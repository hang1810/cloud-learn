package com.hang.service.Impl;

import com.hang.bean.UserFile;
import com.hang.mapper.UserFileMapper;
import com.hang.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Lazy
@Service
public class UserFileServiceImpl implements UserFileService {

//    @Autowired
    @Resource
    private UserFileMapper userFileMapper;

    @Override
    public List<UserFile> getFilesByUserId(Integer id) {
        return userFileMapper.getFilesByUserId(id);
    }


    @Override
    public void SaveFile(UserFile userFile) {
        //进行三目运算判断，文件是否是图片
        String isImg = userFile.getType().startsWith("image")?"是":"否";
        System.out.println(isImg);
        userFile.setIsImg(isImg);
        userFile.setDownCounts(0);
        userFile.setUploadTime(new Date());
        userFileMapper.SaveFile(userFile);
    }

    @Override
    public UserFile getFilesById(Integer id) {
        return userFileMapper.getFilesById(id);
    }

    @Override
    public void update(UserFile userFile) {
        userFileMapper.update(userFile);
    }

    @Override
    public void delete(Integer id) {
        userFileMapper.delete(id);
    }
}
