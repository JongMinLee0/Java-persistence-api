package com.example.demo;

import com.example.demo.entity.Club;
import com.example.demo.entity.Friend;
import com.example.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EntityTest {

    private  EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jongmin");

    @Test
    void saveMemberTest(){
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

    @Test
    void findMemberTest(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jongmin");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            Member member = new Member();
            member.setName("jongmin");
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            Member member1 = entityManager.find(Member.class, member.getMember_id());
            assertEquals("jongmin", member1.getName());

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }

    @Test
    void clubMemberMappingTest(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jongmin");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            Club club = new Club("baskball");
            entityManager.persist(club);

            Friend friend = new Friend("park", "01022221111");
            Member member = new Member("jongmin", 30, friend);
            member.setClub(club);
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            Member findMember = entityManager.find(Member.class, member.getMember_id());
            Club findClub = findMember.getClub();
            assertEquals(member.getClub().getClub_id(), findClub.getClub_id());

            List<Member> members = findClub.getMemberList();
            assertEquals(1, members.size());
            for(Member member1 : members){
                System.out.println("Member Name : " + member1.getName());
            }

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }

}
