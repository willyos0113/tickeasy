package tw.idv.tia203.member.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Integer memberId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "birth_date")
	private Timestamp birthDate;

	@Column(name = "gender")
	private String gender;

	@Column(name = "role_level")
	private Integer roleLevel;

	@Column(name = "is_active")
	private Integer isActive;

	@Column(name = "unicode")
	private String unicode;

	@Column(name = "id_card")
	private String idCard;

	@Column(name = "password")
	private String password;

	@Lob
	@Column(name = "photo")
	private byte[] photo;

	@Column(name = "create_time", insertable = false, updatable = false)
	private Timestamp createTime;

	@Column(name = "update_time", insertable = false, updatable = true)
	private Timestamp updateTime;

}
