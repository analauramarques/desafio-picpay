package com.desafio.picpay.service;

import com.desafio.picpay.entity.Users;
import com.desafio.picpay.enums.TypeUser;
import com.desafio.picpay.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateTransaction(Users sender, BigDecimal amount) throws Exception {
        if (sender.getTypeUser() == TypeUser.MERCHANT){
            throw new Exception("Usuário do tipo lojista não tem autorização para efetuar transações.");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Usuário não possui saldo suficiente para efetuar a transação.");
        }
    }

    public Users findUserById(Long id) throws Exception{
       return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(Users user){
        userRepository.save(user);
    }
}
