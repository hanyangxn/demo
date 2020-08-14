package com.hy.demo.bo;

import com.alibaba.fastjson.JSONObject;
import com.hy.demo.dto.SeriesBo;

import java.util.ArrayList;
import java.util.List;

/**
 * 基金收益出参-货基&非货基
 * */
public class RevenueTargetBo {

    // 曲线集合
    private List<SeriesBo> series;

    // 时间集合
    private List<String> dateUnion;

    public List<SeriesBo> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesBo> series) {
        this.series = series;
    }

    public List<String> getDateUnion() {
        return dateUnion;
    }

    public void setDateUnion(List<String> dateUnion) {
        this.dateUnion = dateUnion;
    }

    @Override
    public String toString() {
        return "RevenueTargetBo{" +
                "series=" + series +
                ", dateUnion=" + dateUnion +
                '}';
    }

    public static void main(String[] args) {

        List<List> list =new ArrayList<>();
        List<String> stringList =new ArrayList<>();
        stringList.add("2");
        stringList.add("20200813");

        List<String> stringList3 =new ArrayList<>();
        stringList3.add("3");
        stringList3.add("20200814");

        List<String> stringList5 =new ArrayList<>();
        stringList5.add("4");
        stringList5.add("20200815");

        List<List> list2 =new ArrayList<>();
        List<String> stringList2 =new ArrayList<>();
        stringList2.add("5");
        stringList2.add("20200813");

        List<String> stringList4 =new ArrayList<>();
        stringList4.add("5");
        stringList4.add("20200813");

        List<String> stringList6 =new ArrayList<>();
        stringList6.add("15");
        stringList6.add("20200815");

        list.add(stringList);
        list.add(stringList5);
        list.add(stringList3);
        list2.add(stringList4);
        list2.add(stringList2);
        list2.add(stringList6);

        SeriesBo seriesBo =new SeriesBo();
        seriesBo.setIndCode("1111");
        seriesBo.setIndName("A");
        seriesBo.setData(list);

        SeriesBo seriesBo2 =new SeriesBo();
        seriesBo2.setIndCode("2222");
        seriesBo2.setIndName("B");
        seriesBo2.setData(list2);

        RevenueTargetBo bo = new RevenueTargetBo();
        List<String> dateUnion = new ArrayList<>();
        List<SeriesBo> seriesBos =new ArrayList<>();
        seriesBos.add(seriesBo);
        seriesBos.add(seriesBo2);
        dateUnion.add("20200813");
        dateUnion.add("20200814");
        dateUnion.add("20200815");
        bo.setDateUnion(dateUnion);
        bo.setSeries(seriesBos);
        System.out.println(JSONObject.toJSONString(bo));

        String s = JSONObject.toJSONString(bo);
        RevenueTargetBo revenueTargetBo = JSONObject.parseObject(s, RevenueTargetBo.class);
        List<String> dateUnion1 = revenueTargetBo.getDateUnion();
        System.out.println("x轴"+dateUnion1.toString());

        List<SeriesBo> series = revenueTargetBo.getSeries();
        for (SeriesBo seriesBo1 :series) {
            List<List> data = seriesBo1.getData();
            System.out.println("类型"+seriesBo1.getIndName());
            for (List list1 :data) {
                System.out.println(list1.get(0));
            }
        }

    }
}
