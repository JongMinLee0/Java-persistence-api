package com.example.demo;

import com.example.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootTest
class EntityTest {

    @Test
    void saveMemberTest(){
        new Persistence();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jongmin");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            Member member = new Member();
            member.setName("jongmin");
            entityManager.persist(member);

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }

}
