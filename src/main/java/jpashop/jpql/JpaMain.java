package jpashop.jpql;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpashop.home.domain.Member;
import jpashop.home.domain.MemberDTO;
import jpashop.home.domain.Team;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
		
		EntityManager em=emf.createEntityManager();
		
		//JPA 의 모든 데이터 변경은  트랜잭션 안에서 실행 되어야 한다.
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			
		try {
			
			Member member=new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);
			
			em.flush();
			em.clear();
			
//			List<Member> result =em.createQuery("select m from Member m", Member.class).getResultList();
//			
//			
//			Member findMember =result.get(0);
//			findMember.setAge(30);
			
			/** 조인을 할경우 다음과 같이 명시적으로 조인을 해라 **/
			//List<Team> result =em.createQuery("select t from Member m join m.team t", Team.class).getResultList();
			
			
			/** 타입이 여러 가지 일경우  */
			/** 방법 1  */
			List resultList =em.createQuery("select m.username, m.age from Member m ").getResultList();
			Object o =resultList.get(0);
			Object[] result =(Object[]) o;
			System.out.println("방법 1 username = " +result[0]);
			System.out.println("방법 1 age = " +result[1]);
			
			
			/** 방법 2  */
			List<Object[]> resultList2 =em.createQuery("select m.username, m.age from Member m ").getResultList();
			Object[] reuslt2=resultList2.get(0);
			System.out.println("방법 2 username = " +reuslt2[0]);
			System.out.println("방법 2 age = " +reuslt2[1]);
			
			
			
			/** 방법 3  항상 new 와 jpql  적고 생성자를 호툴하듯이 하면 된다.*/
			List<MemberDTO> resultList3 =em.createQuery("select new jpashop.home.domain.MemberDTO(m.username, m.age) from Member m ", MemberDTO.class).getResultList();
			MemberDTO memberDTO=resultList3.get(0);
			System.out.println("방법 2 username = " +memberDTO.getUsername());
			System.out.println("방법 2 age = " +memberDTO.getAge());
			
			
			
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
