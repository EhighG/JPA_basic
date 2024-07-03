package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            // 생성
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("helloA");
//
//            em.persist(member);

//            // 삭제
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

//            // 수정
//            Member findMember = em.find(Member.class, 1L); // 조회 시, 먼저 1차 캐시에서 조회한다. 1차 캐시는 트랜잭션 안에서만.
//            findMember.setName("helloJPA"); // jpa가 변경사항을 tx.commit 직전에 모두 파악하고, update 쿼리를 날린다.

//            // JPQL - 전체 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }

//            /*
//            엔티티 생명주기 예제
//             */
//            // 비영속
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("HelloJPA");
//
//            // 영속; 엔티티가 영속성 컨텍스트에 의해 관리된다.
//            em.persist(member);
//
//            // 준영속; 엔티티를 영속성 컨텍스트에서 지운다.
//            em.detach(member);
//
//            // 삭제
//            em.remove(member);

//            // 쓰기 지연
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(151L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("======================");

//            // 변경 감지(더티 체킹)
//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZZ");
//
//            System.out.println("================");
//
//            em.flush(); // 현재까지의 변경사항을 모두 DB에 반영(DB와 em을 동기화) 1차 캐시는 이후에도 남아있는다.

//            // 준영속 상태
//            Member findMember = em.find(Member.class, 150L);
//            em.detach(findMember); // 특정 엔티티만 준영속상태로
//            em.clear(); // 영속상태이던 모든 엔티티들을 준영속상태로. (em을 비움)
//            em.close(); // 영속성 컨텍스트를 종료

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
