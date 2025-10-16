package com.example.LibralyManager.service;

import com.example.LibralyManager.domain.MembershipTier;
import com.example.LibralyManager.domain.User;
import com.example.LibralyManager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountService {

    private final UserRepository userRepository;

    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User topUp(Long userId, long amountVnd) {
        if (amountVnd <= 0) throw new IllegalArgumentException("Amount must be positive");
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setBalanceVnd(user.getBalanceVnd() + amountVnd);
        if (user.isBanned() && user.getBalanceVnd() > 0) {
            user.setBanned(false);
            user.setDeleted(false);
        }
        return userRepository.save(user);
    }

    public User upgradeMembership(Long userId, MembershipTier targetTier) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        long price = switch (targetTier) {
            case ORDINARY -> 0L;
            case ADVANCE -> 20000L;
            case PREMIUM -> 100000L;
        };
        if (user.getBalanceVnd() < price) throw new IllegalStateException("Insufficient balance");
        user.setBalanceVnd(user.getBalanceVnd() - price);
        user.setMembershipTier(targetTier);
        return userRepository.save(user);
    }
}


