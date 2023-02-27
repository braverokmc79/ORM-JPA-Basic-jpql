package jpashop.jpql;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpashop.home.domain.Member;
import jpashop.home.domain.MemberDTO;
import jpashop.home.domain.Team;

public class Main조인 {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
		
		EntityManager em=emf.createEntityManager();
		
		//JPA 의 모든 데이터 변경은  트랜잭션 안에서 실행 되어야 한다.
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			
		try {
			
			Team team=new Team();
			team.setName("teamA");
			em.persist(team);
			
			Member member=new Member();
			member.setUsername("member1");
			member.setAge(10);
			member.changeTeam(team);
			
			em.persist(member);	
	
			em.flush();
			em.clear();
			
			String query="select m  from Member m LEFT join m.team t on t.name='A' ";
			List<Member> result =em.createQuery(query, Member.class).getResultList();
				
			System.out.println(" reuslt : "+result.size());
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
