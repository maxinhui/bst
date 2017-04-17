package top.builbu.business.wechat.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.builbu.business.wechat.dto.WxConfigDTO;
import top.builbu.business.wechat.entity.WxConfig;

@Repository
public interface WxConfigMapper {

    /**
     * 列表
     * @param dto
     * @return
     */
    List<WxConfigDTO> selectByList(@Param("dto")WxConfigDTO dto,@Param("offset")Integer offset,@Param("limit")Integer limit);
    
    /**
     *主键查询
     *
     */
    WxConfig selectByPrimaryKey(Long id); 

   
    /**
     *主键查询
     *
     */
    int deleteByPrimaryKey(Long id); 

      
    /**
     *插入
     *
     */
    int insert(WxConfig record); 
      
    /**
     * 修改
     *
     */  
    int updateByPrimaryKey(WxConfig record);  
    
    /**
     * 批量删除
     *
     */  
    int deleteByAll(@Param("delids")Long[] delids);
    
    int selectByCount(@Param("dto")WxConfigDTO dto);

}
