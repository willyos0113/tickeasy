package tw.idv.tia203.member.entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Member implements UserDetails {
	private static final long serialVersionUID = 1L;

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

	// UserDetails 實現方法
	/**
	 * 定義此用戶擁有哪些權限或角色
	 * @return {Collection} 權限集合
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 根據 roleLevel 決定角色
		String role = switch (roleLevel) {
		case 0 -> "INACTIVE";
		case 1 -> "USER";
		case 2 -> "EVENT_HOST";
		case 3 -> "ADMIN";
		default -> "OTHER";
		};
		return List.of(new SimpleGrantedAuthority("ROLE_" + role)); // 建立一個 immutable 的 List
	}

	/**
	 * 取得用戶登入時使用的帳號
	 * @return {String} 登入識別(用戶名)
	 */
	@Override
	public String getUsername() {
		return userName; // lombok 會略過欄位 userName 的 getter()
	}

	/**
	 * 取得用戶的密碼
	 * @return {String} 密碼(⚠️ 務必確保是加密後的密碼)
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * 檢查用戶是否過期
	 * @return {boolean} 過期或否
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 檢查用戶是否鎖定
	 * @return {boolean} 鎖定或否
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 檢查密碼是否過期
	 * @return {boolean} 過期或否
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 檢查帳戶是否啟用
	 * @return {boolean} 啟用或否
	 */
	@Override
	public boolean isEnabled() {
		return isActive != null && isActive == 1;
	}
}
