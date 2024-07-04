package hellojpa;

import jakarta.persistence.*;

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

            /*
             @MappedSuperClass
             */
            Member member = new Member();
            member.setCreatedBy("kang");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member);

            em.flush();
            em.clear();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
