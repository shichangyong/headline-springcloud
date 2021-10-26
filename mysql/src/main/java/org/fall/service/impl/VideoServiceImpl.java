package org.fall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.ArticlePreviewDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.UserPO;
import org.fall.entity.po.VideoPO;
import org.fall.entity.vo.PreviewByTimeVO;
import org.fall.mapper.VideoPOMapper;
import org.fall.service.api.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoPOMapper videoPOMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveVideo(VideoPO videoPO) {
        videoPOMapper.insertSelective(videoPO);
    }

    @Override
    public List<VideoPO> getListByTimeRemote(PreviewByTimeVO previewByTimeVO) {
        List<VideoPO> listByTime = videoPOMapper.getListByTime(previewByTimeVO);
        return listByTime;
    }

    @Override
    public VideoDTO getVideoPreview(Integer id) {
        VideoDTO articlePreview =videoPOMapper.getVideoPreview(id);
        articlePreview.getUserPO().setPassword(null);
        return articlePreview;
    }

    @Override
    public int addCommentNumber(Integer id) {
        int i = videoPOMapper.addCommentNumber(id);
        return i;
    }

    @Override
    public PageInfo<VideoDTO> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 利用PageHelper的静态方法开启分页
        PageHelper.startPage(pageNum,pageSize);

        // 调用Mapper接口的对应方法
        List<VideoDTO> videoDTOS = videoPOMapper.selectVideoByKeyword(keyword);

        // 为了方便页面的使用，把Admin的List封装成PageInfo（放别得到页码等数据）
        PageInfo<VideoDTO> pageInfo = new PageInfo<>(videoDTOS);

        // 返回得到的pageInfo对象
        return pageInfo;
    }



}
