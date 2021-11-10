package com.jcpdev.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass	// 테이블 생성은 하지않는 부모 클래스
@EntityListeners(value= {AuditingEntityListener.class})
@Getter
public abstract class BaseEntity{
	   @CreatedDate	// insert 할 때, 날짜 시간
	   @Column(name="reg_date",updatable=false) // updatable=false: update쿼리에는 사용하지 않는다.
	   private LocalDateTime regDate;
	   
	   @LastModifiedDate	// insert, update할 때, 날짜 시간
	   @Column(name="mod_date")
	   private LocalDateTime modDate;
}


/*   JPA는 고유한 메모리 메모리 공간(persistance context) 를 이용하여 엔티티 객체를 관리한다.
 *   여기서 관리되는 객체들이 변경되면 결과적으로 데이터베이스에 반영하는 방식이다.
 *   이러한 엔티티 객체에 일어나는 변화를 감시하는 리스너가 있다. 
 *   -> AuditingEntityListener 지정 (Audit 감시, 감사)
 *   AuditingEntityListener 활성화는 @EnableJpaAuditing 설정(main method에 설정)
 */