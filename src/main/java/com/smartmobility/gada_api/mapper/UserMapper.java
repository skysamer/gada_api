package com.smartmobility.gada_api.mapper;

import com.smartmobility.gada_api.dto.user.DeleteMember;
import com.smartmobility.gada_api.dto.user.MemberUpdateForm;
import com.smartmobility.gada_api.dto.user.PasswordUpdateForm;
import com.smartmobility.gada_api.dto.user.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDTO findByCellNum(String cell);
    boolean existsByCellNum(String cell);
    void save(UserDTO userDTO);
    void updateNewPassword(PasswordUpdateForm passwordUpdateForm);
    void updateNewNickname(MemberUpdateForm memberUpdateForm);
    void updateMember(MemberUpdateForm memberUpdateForm);
    boolean existsNickname(String nickname);
    void deleteMember(DeleteMember deleteMember);
}
