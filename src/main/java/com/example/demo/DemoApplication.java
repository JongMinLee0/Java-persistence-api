package com.example.demo;

import com.example.demo.entity.Club;
import com.example.demo.entity.Friend;
import com.example.demo.entity.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

            List<Member> members = findClub.getMemberList();
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
