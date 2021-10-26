package org.fall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.ArticlePreviewDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.ArticlePO;
import org.fall.entity.vo.PreviewByTimeVO;
import org.fall.mapper.ArticlePOMapper;
import org.fall.service.api.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.PastOrPresent;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticlePOMapper articlePOMapper;

    //保存文章
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveArticle(ArticlePO articlePO) {
        articlePOMapper.insertSelective(articlePO);
    }

    @Override
    public ArticlePreviewDTO getArticlePreview(Integer id) {
        ArticlePreviewDTO articlePreview = articlePOMapper.getArticlePreview(id);
        articlePreview.getUserPO().setPassword(null);
        return articlePreview;
    }

    @Override
    public ArticlePO selectByPrimaryKey(Integer id) {
        return articlePOMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public int addCommentNumber(Integer id) {
        int i = articlePOMapper.addCommentNumber(id);
        return i;
    }

    @Override
    public List<ArticlePO> getListByTimeRemote(PreviewByTimeVO previewByTimeVO) {
        List<ArticlePO> listByTime = articlePOMapper.getListByTime(previewByTimeVO);
        return listByTime;
    }

    @Override
    public PageInfo<ArticlePreviewDTO> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 利用PageHelper的静态方法开启分页
        PageHelper.startPage(pageNum,pageSize);

        // 调用Mapper接口的对应方法
        List<ArticlePreviewDTO> articlePreviewDTOS = articlePOMapper.selectArticleByKeyword(keyword);

        // 为了方便页面的使用，把Admin的List封装成PageInfo（放别得到页码等数据）
        PageInfo<ArticlePreviewDTO> pageInfo = new PageInfo<>(articlePreviewDTOS);

        // 返回得到的pageInfo对象
        return pageInfo;
    }
}
