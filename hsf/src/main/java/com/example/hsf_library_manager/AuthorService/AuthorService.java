public interface AuthorService {

  String addAuthor(AuthorSaveDTO authorSaveDTO);

  List<AuthorDTO> getAllAuthor();

  String updateAuthor(AuthorUpdateDTO authorUpdateDTO);

  String deleteAuthor(int id);
}
