package org.fall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.MicroHeadlinesDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.MicroHeadlinesPO;
import org.fall.entity.vo.PreviewByTimeVO;
import org.fall.mapper.MicroHeadlinesPOMapper;
import org.fall.service.api.MicroHeadlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class MicroHeadlinesServiceImpl implements MicroHeadlinesService {

    @Autowired
    MicroHeadlinesPOMapper microHeadlinesPOMapper;

    //保存微头条
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveMicroHeadlines(MicroHeadlinesPO microHeadlinesPO) {
        microHeadlinesPOMapper.insertSelective(microHeadlinesPO);
    }

    @Override
    public MicroHeadlinesDTO getMicroHeadlinesRemote(int id) {
        MicroHeadlinesDTO microHeadlinesDTO = microHeadlinesPOMapper.getMicroHeadlines(id);
        microHeadlinesDTO.getUserPO().setPassword(null);
        return microHeadlinesDTO;
    }

    @Override
    public List<MicroHeadlinesPO> getListByTimeRemote(PreviewByTimeVO previewByTimeVO) {
        List<MicroHeadlinesPO> listByTime = microHeadlinesPOMapper.getListByTime(previewByTimeVO);
        return listByTime;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public int addCommentNumber(Integer id) {
        int i = microHeadlinesPOMapper.addCommentNumber(id);
        return i;
    }

    @Override
    public PageInfo<MicroHeadlinesDTO> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 利用PageHelper的静态方法开启分页
        PageHelper.startPage(pageNum,pageSize);

        // 调用Mapper接口的对应方法
        List<MicroHeadlinesDTO> headlinesDTOS = microHeadlinesPOMapper.selectHeadlinesByKeyword(keyword);

        // 为了方便页面的使用，把Admin的List封装成PageInfo（放别得到页码等数据）
        PageInfo<MicroHeadlinesDTO> pageInfo = new PageInfo<>(headlinesDTOS);

        // 返回得到的pageInfo对象
        return pageInfo;
    }
}
