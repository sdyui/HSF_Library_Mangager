package com.example.LibralyManager.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @Min(0)
    @Column(nullable = false)
    private long balanceVnd = 0L; // in VND

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MembershipTier membershipTier = MembershipTier.ORDINARY;

    @Column(nullable = false)
    private boolean banned = false; // derived by rules when negative balance attempts happen

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public long getBalanceVnd() { return balanceVnd; }
    public void setBalanceVnd(long balanceVnd) { this.balanceVnd = balanceVnd; }
    public MembershipTier getMembershipTier() { return membershipTier; }
    public void setMembershipTier(MembershipTier membershipTier) { this.membershipTier = membershipTier; }
    public boolean isBanned() { return banned; }
    public void setBanned(boolean banned) { this.banned = banned; }
}


