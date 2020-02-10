package com.example.demo;

import com.example.demo.entity.Friend;
import com.example.demo.entity.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jongmin");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        // 트랜잭션 시작
        transaction.begin();
        try{
            Member member = new Member();
            member.setName("jongmin");
            member.setAge(30);

            Friend friend = new Friend();
            friend.setName("park");
            entityManager.persist(member); // 영구저장
            transaction.commit(); // 커밋
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }

}
