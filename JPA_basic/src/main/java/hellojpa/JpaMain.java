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
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

//            // 영속성 전이가 없을 때
//            em.persist(parent);
//            em.persist(child1);
//            em.persist(child2);

//            // 영속성 전이
//            em.persist(parent);
//
//            // 고아 객체 처리
//            em.flush();
//            em.clear();
//
//            Parent findParent = em.find(Parent.class, parent.getId());
//            findParent.getChildList().remove(0); // orphanRemoval 동작(delete 쿼리 날아감)

            // orphanRemoval; 부모 엔티티 삭제시, 자식 엔티티까지 삭제된다. (CascadeType.REMOVE처럼 동작)
            em.persist(parent);
            em.persist(child1);
            em.persist(child2);

            Parent findParent = em.find(Parent.class, parent.getId());
            em.remove(findParent); // child 1, 2까지 삭제됨

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
