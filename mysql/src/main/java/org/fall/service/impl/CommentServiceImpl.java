package org.fall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.CommentDTO;
import org.fall.entity.po.CommentPO;
import org.fall.mapper.CommentPOMapper;
import org.fall.service.api.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = true)
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentPOMapper commentPOMapper;

    @Override
    public PageInfo<CommentDTO> getPageInfo(String targetType, Integer targetId, Integer pageNum, Integer pageSize) {
        // 利用PageHelper的静态方法开启分页
        PageHelper.startPage(pageNum,pageSize);

        // 调用Mapper接口的对应方法
        List<CommentDTO> byTarget = commentPOMapper.getByTarget(targetType, targetId);

        // 为了方便页面的使用，把List封装成PageInfo（放别得到页码等数据）
        PageInfo<CommentDTO> pageInfo = new PageInfo<>(byTarget);

        // 返回得到的pageInfo对象
        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public int saveComment(CommentPO commentPO) {
        int i = commentPOMapper.insertSelective(commentPO);
        return i;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public int addRepliesNumber(int id) {
        int i = commentPOMapper.addRepliesNumber(id);
        return i;
    }

}
