package com.music.recommend.core;

import com.google.common.collect.Lists;
import com.music.recommend.dto.RelateDTO;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoreMath {
    /**
     * 推荐
     * @param userId 用户id
     * @param list 推荐的idList集合
     * @return
     */
    public List<Integer> recommend(Integer userId, List<RelateDTO> list) {
        //找到最近邻用户id
        Map<Double, Integer> distances = computeNearestNeighbor(userId, list);
        //取出相似度最近的用户id
        Integer nearest = distances.values().iterator().next();
        Iterator<Integer> iterator = distances.values().iterator();
        while(iterator.hasNext()){
            nearest = iterator.next();
        }
        Map<Integer, List<RelateDTO>> userMap =list.stream().collect(Collectors.groupingBy(RelateDTO::getUserId));

        //最近邻用户买过的商品id列表
        List<Integer> neighborItemList = userMap.get(nearest).stream().map(e->e.getProductId()).collect(Collectors.toList());
        //指定用户买过的商品id列表
        List<Integer> userItemList  = userMap.get(userId).stream().map(e->e.getProductId()).collect(Collectors.toList());

        //找到最近邻买过，但是该用户没买过的商品id，计算推荐，放入推荐列表
        List<Integer> recommendList = new ArrayList<>();
        for (Integer item : neighborItemList) {
            if (!userItemList.contains(item)) {
                recommendList.add(item);
            }
        }
        Collections.sort(recommendList);
        return recommendList;
    }

    /**
     * 在给定userId的情况下，计算其他用户和它的相关系数并排序
     * @param userId
     * @param list
     * @return
     */
    private Map<Double, Integer> computeNearestNeighbor(Integer userId, List<RelateDTO> list) {
        Map<Integer, List<RelateDTO>> userMap = list.stream().collect(Collectors.groupingBy(RelateDTO::getUserId));
        //treemap是从小到大排好序的
        Map<Double, Integer> distances = new TreeMap<>();
        userMap.forEach((k,v)->{
            if(k.intValue() != userId.intValue()){
                double distance = pearson_dis(v,userMap.get(userId));
                distances.put(distance, k);
            }
        });
        return distances;
    }

    /**
     * 计算两个序列间的相关系数
     *
     * @param xList 其他用户的数据集
     * @param yList 当前用户的数据集
     * @return
     */
    private double pearson_dis(List<RelateDTO> xList, List<RelateDTO> yList) {
        List<Integer> xs= Lists.newArrayList();
        List<Integer> ys= Lists.newArrayList();
        xList.forEach(x->{
            yList.forEach(y->{
                if(x.getProductId().intValue() == y.getProductId().intValue()){
                    xs.add(x.getIndex());
                    ys.add(y.getIndex());
                }
            });
        });
        return getRelate(xs,ys);
    }

    /**
     * 方法描述: 皮尔森（pearson）相关系数计算
     * (x1,y1) 理解为 a 用户对 x 商品的点击次数和对 y 商品的点击次数
     * @param xs
     * @param ys
     * @Return {@link Double}
     * @throws
     * @author tarzan
     * @date 2020年07月31日 17:03:20
     */
    public static Double getRelate(List<Integer> xs, List<Integer> ys){
        int n=xs.size();
        double Ex= xs.stream().mapToDouble(x->x).sum();
        double Ey=ys.stream().mapToDouble(y->y).sum();
        double Ex2=xs.stream().mapToDouble(x-> Math.pow(x,2)).sum();
        double Ey2=ys.stream().mapToDouble(y-> Math.pow(y,2)).sum();
        double Exy= IntStream.range(0,n).mapToDouble(i->xs.get(i)*ys.get(i)).sum();
        double numerator=Exy-Ex*Ey/n;
        double denominator= Math.sqrt((Ex2- Math.pow(Ex,2)/n)*(Ey2- Math.pow(Ey,2)/n));
        if (denominator==0) {
            return 0.0;
        }
        return numerator/denominator;
    }
}
