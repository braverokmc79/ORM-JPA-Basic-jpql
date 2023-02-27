package jpashop.jpql;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpashop.home.domain.Member;

public class JpaMainPaging {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
		
		EntityManager em=emf.createEntityManager();
		
		//JPA 의 모든 데이터 변경은  트랜잭션 안에서 실행 되어야 한다.
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			
		try {
			
			for(int i=0; i<100;  i++) {
				Member member=new Member();
				member.setUsername("member"+i);
				member.setAge(i);
				em.persist(member);	
			}
			
			em.flush();
			em.clear();
			
			List<Member> result =em.createQuery("select m from Member m order by m.age desc", Member.class)
					.setFirstResult(1)
					.setMaxResults(10)
					.getResultList();
			
			System.out.println("result.size = "+result.size());
			for(Member meber1: result) {
				System.out.println("meber = "+meber1);
			}
			
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
	
		
		emf.close();	
	}
	

}
