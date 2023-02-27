package jpashop.home.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	
	private String username;
	private int age;
	private Member member;
	private Team team;
	
	public MemberDTO(Member member, Team team) {
		this.member = member;
		this.team=team;
	}
	
	public MemberDTO(String username, int age , Team team) {
		this.username = username;
		this.age = age;
		this.team=team;
	}
	
}
