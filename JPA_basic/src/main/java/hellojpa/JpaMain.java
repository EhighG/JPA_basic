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
            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            // DB에서 조회를 위해, 1차 캐시 날리기
            em.flush();
            em.clear();

//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = " + findMovie);

//            /*
//            상속 관계에 있는 엔티티들을 별개의 테이블 전략으로 구현하면, (부모 타입으로)조회를 했을 때 union all 한 후 찾는다.(비효율적이다.)
//             */
//            Item item = em.find(Item.class, movie.getId());
//            System.out.println("item = " + item);

//            /*
//             @MappedSuperClass
//             */
//            Member member = new Member();
//            member.setCreatedBy("kang");
//            member.setCreatedDate(LocalDateTime.now());
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();

            /*
            프록시, 즉시 로딩, 지연 로딩
             */
//            // 문제 상황; 다른 방식의 사용이 모두 필요할 때, 어떻게 최적화해야 하나?
//            Member member = em.find(Member.class, 1L);
//
//            printMember(member);
//            printMemberAndTeam(member);

//            // em.getReference()
//            Member member = new Member();
//            member.setUsername("hello");
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
////            Member findMember = em.find(Member.class, member.getId());
//            Member findMember = em.getReference(Member.class, member.getId());
//
//            System.out.println("findMember.getClass() = " + findMember.getClass()); // class hellojpa.Member$HibernateProxy$8Yrhrpif
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getUsername() = " + findMember.getUsername());

//            // 타입 체크 시 주의사항 : == 대신 instanceof를 사용한다.
//            Member member1 = new Member();
//            member1.setUsername("member1");
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("member2");
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            Member m1 = em.find(Member.class, member1.getId());
//            Member m2 = em.getReference(Member.class, member2.getId());
//
//            System.out.println("(m1.getClass() == m2.getClass()) = " + (m1.getClass() == m2.getClass())); // false

//            // 영속성 컨텍스트에 이미 찾는 엔티티가 있으면, em.getReference()를 해도 실제 엔티티를 반환한다.
//            Member member = new Member();
//            member.setUsername("member1");
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            Member m = em.find(Member.class, member.getId());
//            System.out.println("m.getClass() = " + m.getClass());
//
//            Member mRef = em.getReference(Member.class, member.getId());
//            System.out.println("mRef.getClass() = " + mRef.getClass()); // class hellojpa.Member
//
//            // JPA에서는, 같은 트랜잭션 안에서 (a == a)는 항상 true이다. (JPA가 보장해준다.)
//            System.out.println("(m == mRef) = " + (m == mRef));

//            Member member1 = new Member();
//            member1.setUsername("member1");
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            Member ref = em.getReference(Member.class, member1.getId());
//            System.out.println("ref.getClass() = " + ref.getClass()); // proxy
//
//            Member findMember = em.find(Member.class, member1.getId());
//            System.out.println("findMember.getClass() = " + findMember.getClass()); // proxy!! 위랑 같은 / ==비교 = true 보장을 위해
//
//            System.out.println("ref == findMember = " + (ref == findMember)); // true!!

//            /* 준영속 상태에서의 프록시 초기화 시도 -> 예외 발생!(LazyInitializationException)
//            해당 예외는 특히나, 트랜잭션이 끝나고 나서 엔티티를 조회하고자 할 때 많이 발생한다.
//            영속성 컨텍스트의 생성, 삭제 시기는 트랜잭션 시작, 종료 시기와 맞춰서 설계하므로
//             */
//            Member member1 = new Member();
//            member1.setUsername("member1");
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember.getClass() = " + refMember.getClass());
//
//            // 셋 중 뭘 하든, 준영속 상태가 된다.
//            em.detach(refMember);
////            em.close();
////            em.clear();
//
//            refMember.getUsername();
//            /*
//            org.hibernate.LazyInitializationException: could not initialize proxy [hellojpa.Member#1] - no Session
//                at org.hibernate.proxy.AbstractLazyInitializer.initialize(AbstractLazyInitializer.java:165)
//                at org.hibernate.proxy.AbstractLazyInitializer.getImplementation(AbstractLazyInitializer.java:314)
//                at org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor.intercept(ByteBuddyInterceptor.java:44)
//                at org.hibernate.proxy.ProxyConfiguration$InterceptorDispatcher.intercept(ProxyConfiguration.java:102)
//                at hellojpa.Member$HibernateProxy$MNWIkEwF.getUsername(Unknown Source)
//                at hellojpa.JpaMain.main(JpaMain.java:139)
//             */

            // JPA 프록시와 관련된 유틸리티 메소드들

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember.getClass() = " + refMember.getClass());

            // 프록시 초기화가 된 상태인지
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
            // 프록시 강제 초기화(JPA 표준이 아닌, Hibernate가 제공)
            Hibernate.initialize(refMember);


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
