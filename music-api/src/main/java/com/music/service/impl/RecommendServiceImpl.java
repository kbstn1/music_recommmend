package com.music.service.impl;

import com.google.common.collect.Lists;
import com.music.dao.RankMapper;
import com.music.dao.SongMapper;
import com.music.domain.Rank;
import com.music.domain.Song;
import com.music.recommend.core.CoreMath;
import com.music.recommend.dto.ProductDTO;
import com.music.recommend.dto.RelateDTO;
import com.music.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private RankMapper rankMapper;

    @Autowired
    private SongMapper songMapper;

    @Override
    public List<RelateDTO> getRankData() {
        List<RelateDTO> relateList = Lists.newArrayList();
        List<Rank> rankList = rankMapper.selectRanks();
        for (Rank rank : rankList) {
            RelateDTO relateDTO = new RelateDTO();
            relateDTO.setUserId(rank.getUserId());
            relateDTO.setProductId(rank.getSongId());
            relateDTO.setIndex(rank.getScore());
            relateList.add(relateDTO);
        }
        if(CollectionUtils.isEmpty(relateList)){
            System.out.println("--------------------List<RelateDTO>为空！");
        }
        return relateList;
    }

    @Override
    public List<ProductDTO> getSongData() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Song> songLists = songMapper.allSong();
        for (Song song : songLists) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(song.getId());
            productDTOList.add(productDTO);
        }
        if(CollectionUtils.isEmpty(productDTOList)){
            System.out.println("----------------------List<ProductDTO>为空！");
        }
        return productDTOList;
    }

    @Override
    public List<Song> recommendSongByRank(Integer userId){
        CoreMath coreMath = new CoreMath();
        List<RelateDTO> data = getRankData();
        //执行推荐算法
        List<Integer> recommendations = coreMath.recommend(userId, data);
        //根据返回的商品ids 从getProductData()进行过滤出 所有ProductDTO
        List<ProductDTO> productDTOS = getSongData();
        List<ProductDTO> productDTOList= productDTOS.stream().filter(e->recommendations.contains(e.getProductId())).collect(Collectors.toList());
        //如果推荐id为空
        if(CollectionUtils.isEmpty(productDTOList)){
            System.out.println("-----------推荐的歌单id集为空！");
            return songMapper.allSong();
        }
        List<Song> songList = new ArrayList<>();
        List<Integer> productIdList = productDTOList.stream().map(e -> e.getProductId()).collect(Collectors.toList());
        for (Integer productId : productIdList) {
            Song song1 = songMapper.selectByPrimaryKey(productId);
            songList.add(song1);
        }
        if(CollectionUtils.isEmpty(songList)){
            return songMapper.allSong();
        }
        return songList;
    }
}
