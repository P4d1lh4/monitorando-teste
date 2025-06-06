package br.com.cesarschool.domain.repository.user;

public interface LoginUserRepository<T> {
    T loginUser(String email, String password);
    Boolean hasLoginUser(Long id);

    void logoutUser(Long id);
}
