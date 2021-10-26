package org.fall.test;

import org.fall.entity.dto.ArticlePreviewDTO;
import org.fall.entity.dto.CommentDTO;
import org.fall.entity.dto.MicroHeadlinesDTO;
import org.fall.entity.po.ArticlePO;
import org.fall.entity.po.MicroHeadlinesPO;
import org.fall.entity.po.UserPO;
import org.fall.entity.vo.PreviewByTimeVO;
import org.fall.mapper.ArticlePOMapper;
import org.fall.mapper.CommentPOMapper;
import org.fall.mapper.MicroHeadlinesPOMapper;
import org.fall.mapper.UserPOMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class MyBatisTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserPOMapper userPOMapper;

    @Autowired
    MicroHeadlinesPOMapper microHeadlinesPOMapper;

    @Autowired
    ArticlePOMapper articlePOMapper;

    @Autowired
    CommentPOMapper commentPOMapper;

    Logger logger = LoggerFactory.getLogger(MyBatisTest.class);

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        logger.info("connection:   " + connection);
        connection.close();
    }

    @Test
    public void testMybatis01(){
        int i = userPOMapper.insert(new UserPO(null,"17623138621","网络异常","2000119520","个人",null));
        System.err.println("操作"+i+"完成");
    }

    @Test
    public void testMybatis02(){
        int i = microHeadlinesPOMapper.insertSelective(new MicroHeadlinesPO(null,1,"",null,new Date(),null,null));
        System.err.println("操作"+i+"完成");
    }

    @Test
    public void testMybatis03(){
        int i = articlePOMapper.insertSelective(new ArticlePO(null,1,
                "[2021/10/01/6c77653c57b3b8f45d1a412a4042a1ce.jpg, 2021/10/01/94974a6cd821923c63d5d1aee08d2077.jpg, 2021/10/01/e9108aaaae6a2235ce65170f8d918bcd.jpg][2021/10/01/6c77653c57b3b8f45d1a412a4042a1ce.jpg, 2021/10/01/94974a6cd821923c63d5d1aee08d2077.jpg, 2021/10/01/e9108aaaae6a2235ce65170f8d918bcd.jpg][2021/10/01/6c77653c57b3b8f45d1a412a4042a1ce.jpg, 2021/10/01/94974a6cd821923c63d5d1aee08d2077.jpg, 2021/10/01/e9108aaaae6a2235ce65170f8d918bcd.jpg][2021/10/01/6c77653c57b3b8f45d1a412a4042a1ce.jpg, 2021/10/01/94974a6cd821923c63d5d1aee08d2077.jpg, 2021/10/01/e9108aaaae6a2235ce65170f8d918bcd.jpg][2021/10/01/6c77653c57b3b8f45d1a412a4042a1ce.jpg, 2021/10/01/94974a6cd821923c63d5d1aee08d2077.jpg, 2021/10/01/e9108aaaae6a2235ce65170f8d918bcd.jpg][2021/10/01/6c77653c57b3b8f45d1a412a4042a1ce.jpg, 2021/10/01/94974a6cd821923c63d5d1aee08d2077.jpg, 2021/10/01/e9108aaaae6a2235ce65170f8d918bcd.jpg][2021/10/01/6c77653c57b3b8f45d1a412a4042a1ce.jpg, 2021/10/01/94974a6cd821923c63d5d1aee08d2077.jpg, 2021/10/01/e9108aaaae6a2235ce65170f8d918bcd.jpg][2021/10/01/6c77653c57b3b8f45d1a412a4042a1ce.jpg, 2021/10/01/94974a6cd821923c63d5d1aee08d2077.jpg, 2021/10/01/e9108aaaae6a2235ce65170f8d918bcd.jpg]",
                "[2021/10/01/6c77653c57b3b8f45d1a412a4042a1ce.jpg, 2021/10/01/94974a6cd821923c63d5d1aee08d2077.jpg, 2021/10/01/e9108aaaae6a2235ce65170f8d918bcd.jpg]",
                "[2021/10/01/6c77653c57b3b8f45d1a412a4042a1ce.jpg, 2021/10/01/94974a6cd821923c63d5d1aee08d2077.jpg, 2021/10/01/e9108aaaae6a2235ce65170f8d918bcd.jpg]",
                "2021/10/01/e1108aaaae6a2235ce65170f8d918bcd.mp4",new Date(),null,null,"test1"));
        System.err.println("操作"+i+"完成");
    }

    @Test
    public void testMybatis04(){
        MicroHeadlinesDTO microHeadlinesDTO = microHeadlinesPOMapper.getMicroHeadlines(6);
        //MicroHeadlinesPO microHeadlinesPO = microHeadlinesPOMapper.selectByPrimaryKey(5);
        System.err.println(microHeadlinesDTO);
    }

    @Test
    public void testMybatis05(){
        UserPO userPO = userPOMapper.selectByPrimaryKey(1);
        //MicroHeadlinesPO microHeadlinesPO = microHeadlinesPOMapper.selectByPrimaryKey(5);
        System.err.println(userPO);
    }

    @Test
    public void testMybatis06(){
        ArticlePreviewDTO articlePreview = articlePOMapper.getArticlePreview(6);
        ArticlePO articlePO = articlePOMapper.selectByPrimaryKey(9);
        System.err.println(articlePO);
    }

    @Test
    public void testMybatis07(){
//        MicroHeadlinesPO microHeadlinesPO1 = microHeadlinesPOMapper.selectByPrimaryKey(5);
//        System.err.println(microHeadlinesPO1);
        PreviewByTimeVO previewByTimeVO = new PreviewByTimeVO("2021-10-04 20:53:34","newest");
        List<MicroHeadlinesPO> listByTime = microHeadlinesPOMapper.getListByTime(previewByTimeVO);
        for (MicroHeadlinesPO microHeadlinesPO : listByTime) {
            System.err.println(microHeadlinesPO);
        }
    }

    @Test
    public void testMybatis08(){
        List<CommentDTO> byTarget = commentPOMapper.getByTarget("article",9);
        for (CommentDTO commentDTO : byTarget) {
            System.err.println(commentDTO);
        }
    }

    @Test
    public void testMybatis09(){
        UserPO userPO = userPOMapper.selectByPhone("17623138621");
        System.err.println(userPO);
    }

    @Test
    public void testMybatis10(){
        int i = commentPOMapper.addRepliesNumber(5);
        System.err.println(i);
    }
}
