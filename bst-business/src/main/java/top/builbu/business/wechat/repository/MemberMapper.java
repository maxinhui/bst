package top.builbu.business.wechat.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.builbu.business.wechat.dto.MemberDTO;
import top.builbu.business.wechat.entity.Member;

@Repository
public interface MemberMapper {

    /**
     * 列表
     * @param dto
     * @return
     */
    List<MemberDTO> selectByList(@Param("dto")MemberDTO dto,@Param("offset")Integer offset,@Param("limit")Integer limit);
    
    /**
     *主键查询
     *
     */
    Member selectByPrimaryKey(@Param("openId")String openId); 

    Member selectByOpenId(@Param("openId")String openId); 
    /**
     *主键查询
     *
     */
    int deleteByPrimaryKey(Long member_id); 

      
    /**
     *插入
     *
     */
    int insert(Member record); 
      
    /**
     * 修改
     *
     */  
    int updateByPrimaryKey(Member record);  
    
    /**
     * 批量删除
     *
     */  
    int deleteByAll(@Param("delids")Long[] delids);
    
    int selectByCount(@Param("dto")MemberDTO dto);

}
