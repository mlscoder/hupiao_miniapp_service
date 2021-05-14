package com.adong.start.service.impl;

import com.adong.start.dao.ImageDao;
import com.adong.start.model.Image;
import com.adong.start.service.ImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ImageServcieImpl extends ServiceImpl<ImageDao, Image>
        implements ImageService {
}
