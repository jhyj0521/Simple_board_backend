package jh.SimpleBoard.mapper;

import jh.SimpleBoard.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {

    void save(Member member);

    int findById(String memberId);

    String getMemberSalt(String memberId);

    int idPassCheck(Member member);

    String getMemberNo(String memberId);

    String getMemberName(String memberId);
}
