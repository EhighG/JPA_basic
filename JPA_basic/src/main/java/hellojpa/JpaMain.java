package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            em.flush();
            em.clear();

//            Member m = em.find(Member.class, member1.getId()); // TEAM과 조인하지 않은 select쿼리가 실행됨
//            System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass()); // proxy
//
//            System.out.println("=================");
//            System.out.println("m.getTeam().getName() = " + m.getTeam().getName()); // TEAM select 쿼리 실행
//            System.out.println("=================");

            // 즉시 로딩과 N+1 문제
            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

            /*
            N+1 문제란?
            자동적으로 쿼리해 주는 em.find()와 달리, JPQL을 쓸 땐 그게 그대로 SQL로 번역되어 날려진다.
            즉, 아래의 쿼리를 실행하면 Member 정보와, Team ID만을 가져온다는 말.
            그런데 Member 엔티티의 Team은 즉시 로딩으로 설정되어 있으므로, 해당 설정에 맞춰주기 위해
            하나하나 그 FK로 Team을 조회하는 쿼리를 날린다.(Team 정보가 N개면, N번)
            따라서 총 N+1번의 쿼리를 날린다. 즉,
            select * from Member; * 1
            select * from Team where TEAM_ID = xx; * N
             */
            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }
}
