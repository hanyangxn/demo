package com.example.demo2.service.impl;

import com.example.demo2.bo.StageUpAndDownReturnBo;
import com.example.demo2.domain.Aindexeodprices;
import com.example.demo2.domain.ChinaMutualFundDescription;
import com.example.demo2.domain.Chinamfperformance;
import com.example.demo2.dto.StageUpAndDownDto;
import com.example.demo2.emun.DefaultIndexId;
import com.example.demo2.emun.FundDayCount;
import com.example.demo2.emun.StageUpAndDown;
import com.example.demo2.mapper.AindexeodpricesMapper;
import com.example.demo2.mapper.ChinaMutualFundDescriptionMapper;
import com.example.demo2.mapper.ChinamfperformanceMapper;
import com.example.demo2.result.MsgUtil;
import com.example.demo2.service.StageUpAndDownService;
import com.example.demo2.util.CONST;
import com.example.demo2.util.Constant;
import com.example.demo2.util.DateUtil;
import com.example.demo2.util.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static com.example.demo2.util.DateUtil.*;

@Service
public class StageUpAndDownServiceImpl implements StageUpAndDownService {

    @Resource
    private MessageSource msgsrc;

    @Autowired
    private ChinamfperformanceMapper chinamfperformanceMapper;

    @Autowired
    private AindexeodpricesMapper aindexeodpricesMapper;

    @Autowired
    private ChinaMutualFundDescriptionMapper chinaMutualFundDescriptionMapper;

    @Override
    public ResultDto<StageUpAndDownReturnBo> getStageUpAndDown(StageUpAndDownDto stageUpAndDownDto) throws ParseException {

        StageUpAndDownReturnBo bo = new StageUpAndDownReturnBo();
        List<StageUpAndDownReturnBo.StageUpAndDowns> stageUpAndDownList = new ArrayList<>();
        Chinamfperformance chinamfperformance = chinamfperformanceMapper.
                selectByFundCode(stageUpAndDownDto.getFundCode());

        StageUpAndDown[] values = StageUpAndDown.values();
        for (StageUpAndDown stage : values) {
            StageUpAndDownReturnBo.StageUpAndDowns stageUpAndDowns =
                    new StageUpAndDownReturnBo.StageUpAndDowns();
            if (StageUpAndDown.THE_FUND.getCode().
                    equals(stage.getCode())) {

                stageUpAndDowns.setName(stage.getName());
                stageUpAndDowns.setStageUpAndDownsList(addTheFundList(chinamfperformance));
            } else if (StageUpAndDown.Average_Same_Kind.getCode().
                    equals(stage.getCode())) {

                stageUpAndDowns.setName(stage.getName());
                stageUpAndDowns.setStageUpAndDownsList(addAverageSameKindList(chinamfperformance));
            } else if (StageUpAndDown.SH_300.getCode().
                    equals(stage.getCode())) {

                stageUpAndDowns.setName(stage.getName());
                stageUpAndDowns.setStageUpAndDownsList(addSH300List(DefaultIndexId.HS_300.getIndexIds().get(0)));
            } else if (StageUpAndDown.Similar_Ranking.getCode().
                    equals(stage.getCode())) {

                stageUpAndDowns.setName(stage.getName());
                stageUpAndDowns.setStageUpAndDownsList(addSimilarRankingList(chinamfperformance));
            } else if (StageUpAndDown.Similar_Changes.getCode().
                    equals(stage.getCode())) {

                Chinamfperformance chinamfperformanceNew = chinamfperformanceMapper
                        .selectNewByFundCode(stageUpAndDownDto.getFundCode(), chinamfperformance.getTradeDt());
                stageUpAndDowns.setName(stage.getName());
                stageUpAndDowns.setStageUpAndDownsList(addSimilarChangeList(chinamfperformance,chinamfperformanceNew));
            } else if (StageUpAndDown.Third_Ranking.getCode().
                    equals(stage.getCode())) {

                stageUpAndDowns.setName(stage.getName());
                stageUpAndDowns.setStageUpAndDownsList(addThirdRankingList(chinamfperformance));
            }
            stageUpAndDownList.add(stageUpAndDowns);
        }
        bo.setTradeDt(chinamfperformance.getTradeDt());
        bo.setStageUpAndDownList(stageUpAndDownList);

        return MsgUtil.success(bo, CONST.RETURN_OK, msgsrc);

    }
    //本基金
    public List<Map<String, ?>> addTheFundList(Chinamfperformance chinamfperformance) {

        List<Map<String, ?>> theFundList = new ArrayList<>();
        Map<String, BigDecimal> theFundMap = new HashMap<>();
        FundDayCount[] values = FundDayCount.values();
        for (FundDayCount value :
                values) {
            if (FundDayCount.ONE_WEEK.getId().equals(value.getId())) {
                theFundMap.put(value.getId(), chinamfperformance.getFAvgreturnWeek());
            } else if (FundDayCount.ONE_MONTH.getId().equals(value.getId())) {
                theFundMap.put(value.getId(), chinamfperformance.getFAvgreturnMonth());
            } else if (FundDayCount.THREE_MONTHS.getId().equals(value.getId())) {
                theFundMap.put(value.getId(), chinamfperformance.getFAvgreturnQuarter());
            } else if (FundDayCount.SIX_MONTHS.getId().equals(value.getId())) {
                theFundMap.put(value.getId(), chinamfperformance.getFAvgreturnHalfyear());
            } else if (FundDayCount.SINCE_THIS_YEAR.getId().equals(value.getId())) {
                theFundMap.put(value.getId(), chinamfperformance.getFAvgreturnThisyear());
            } else if (FundDayCount.SINCE_THIS_ESTABLISH.getId().equals(value.getId())) {
                theFundMap.put(value.getId(), chinamfperformance.getFAvgreturnSincefound());
            } else if (FundDayCount.ONE_YEAR.getId().equals(value.getId())) {
                theFundMap.put(value.getId(), chinamfperformance.getFAvgreturnYear());
            } else if (FundDayCount.TWO_YEARS.getId().equals(value.getId())) {
                theFundMap.put(value.getId(), chinamfperformance.getFAvgreturnTwoyear());
            } else if (FundDayCount.THREE_YEARS.getId().equals(value.getId())) {
                theFundMap.put(value.getId(), chinamfperformance.getFAvgreturnThreeyear());
            } else if (FundDayCount.FIVE_YEARS.getId().equals(value.getId())) {
                theFundMap.put(value.getId(), chinamfperformance.getFAvgreturnFiveyear());
            }
            theFundList.add(theFundMap);
        }
        return theFundList;
    }
    //同类平均
    public List<Map<String, ?>> addAverageSameKindList(Chinamfperformance chinamfperformance) {

        List<Map<String, ?>> averageSameKindList = new ArrayList<>();
        Map<String, BigDecimal> averageSameKindMap = new HashMap<>();
        FundDayCount[] values = FundDayCount.values();
        for (FundDayCount value :
                values) {
            if (FundDayCount.ONE_WEEK.getId().equals(value.getId())) {
                averageSameKindMap.put(value.getId(), chinamfperformance.getFSfreturnRecentweek());
            } else if (FundDayCount.ONE_MONTH.getId().equals(value.getId())) {
                averageSameKindMap.put(value.getId(), chinamfperformance.getFSfreturnRecentmonth());
            } else if (FundDayCount.THREE_MONTHS.getId().equals(value.getId())) {
                averageSameKindMap.put(value.getId(), chinamfperformance.getFSfreturnRecentquarter());
            } else if (FundDayCount.SIX_MONTHS.getId().equals(value.getId())) {
                averageSameKindMap.put(value.getId(), chinamfperformance.getFSfreturnRecenthalfyear());
            } else if (FundDayCount.SINCE_THIS_YEAR.getId().equals(value.getId())) {
                averageSameKindMap.put(value.getId(), chinamfperformance.getFSfreturnThisyear());
            } else if (FundDayCount.SINCE_THIS_ESTABLISH.getId().equals(value.getId())) {
                averageSameKindMap.put(value.getId(), chinamfperformance.getFSfreturnSincefound());
            } else if (FundDayCount.ONE_YEAR.getId().equals(value.getId())) {
                averageSameKindMap.put(value.getId(), chinamfperformance.getFSfreturnRecentyear());
            } else if (FundDayCount.TWO_YEARS.getId().equals(value.getId())) {
                averageSameKindMap.put(value.getId(), chinamfperformance.getFSfreturnRecenttwoyear());
            } else if (FundDayCount.THREE_YEARS.getId().equals(value.getId())) {
                averageSameKindMap.put(value.getId(), chinamfperformance.getFSfreturnRecentthreeyear());
            } else if (FundDayCount.FIVE_YEARS.getId().equals(value.getId())) {
                averageSameKindMap.put(value.getId(), chinamfperformance.getFSfreturnRecentfiveyear());
            }
            averageSameKindList.add(averageSameKindMap);
        }
        return averageSameKindList;
    }
    //深沪300
    public List<Map<String, ?>> addSH300List(String windCode) throws ParseException {

        List<Map<String, ?>> SH300List = new ArrayList<>();
        Map<String, BigDecimal> SH300Map = new HashMap<>();
        FundDayCount[] values = FundDayCount.values();
        for (FundDayCount value :
                values) {
            String now = DateUtil.now(DATE_FORMAT2);
            if (FundDayCount.ONE_WEEK.getId().equals(value.getId())) {

                String oneWeek = DateUtil.addWeeks(now, FundDayCount.ONE_WEEK.getCount(), DATE_FORMAT2);
                List<Aindexeodprices> aindexeodprices = aindexeodpricesMapper
                        .selectByWindCode(windCode, oneWeek, now);
                SH300Map.put(value.getId(), HS_300(aindexeodprices));
            } else if (FundDayCount.ONE_MONTH.getId().equals(value.getId())) {

                String oneMonth = DateUtil.addMonths(now, FundDayCount.ONE_MONTH.getCount(), DATE_FORMAT2);
                List<Aindexeodprices> aindexeodprices = aindexeodpricesMapper
                        .selectByWindCode(windCode, oneMonth, now);
                SH300Map.put(value.getId(), HS_300(aindexeodprices));
            } else if (FundDayCount.THREE_MONTHS.getId().equals(value.getId())) {

                String threeMonth = DateUtil.addMonths(now, FundDayCount.THREE_MONTHS.getCount(), DATE_FORMAT2);
                List<Aindexeodprices> aindexeodprices = aindexeodpricesMapper
                        .selectByWindCode(windCode, threeMonth, now);
                SH300Map.put(value.getId(), HS_300(aindexeodprices));
            } else if (FundDayCount.SIX_MONTHS.getId().equals(value.getId())) {

                String sixMonth = DateUtil.addMonths(now, FundDayCount.SIX_MONTHS.getCount(), DATE_FORMAT2);
                List<Aindexeodprices> aindexeodprices = aindexeodpricesMapper
                        .selectByWindCode(windCode, sixMonth, now);
                SH300Map.put(value.getId(), HS_300(aindexeodprices));
            } else if (FundDayCount.SINCE_THIS_YEAR.getId().equals(value.getId())) {

                String sinceThisYear = localDateToString(LocalDate.now().
                        with(TemporalAdjusters.firstDayOfYear()), DATE_FORMAT2);
                List<Aindexeodprices> aindexeodprices = aindexeodpricesMapper
                        .selectByWindCode(windCode, sinceThisYear, now);
                SH300Map.put(value.getId(), HS_300(aindexeodprices));
            } else if (FundDayCount.SINCE_THIS_ESTABLISH.getId().equals(value.getId())) {

                ChinaMutualFundDescription chinaMutualFundDescription = chinaMutualFundDescriptionMapper
                        .selectFundInfoByWindcode(windCode);
                String sinceThisEstablish = chinaMutualFundDescription.getfInfoSetupdate();
                List<Aindexeodprices> aindexeodprices = aindexeodpricesMapper
                        .selectByWindCode(windCode, sinceThisEstablish, now);
                SH300Map.put(value.getId(), HS_300(aindexeodprices));
            } else if (FundDayCount.ONE_YEAR.getId().equals(value.getId())) {

                String oneYear = DateUtil.addMonths(now, FundDayCount.ONE_YEAR.getCount(), DATE_FORMAT2);
                List<Aindexeodprices> aindexeodprices = aindexeodpricesMapper
                        .selectByWindCode(windCode, oneYear, now);
                SH300Map.put(value.getId(), HS_300(aindexeodprices));
            } else if (FundDayCount.TWO_YEARS.getId().equals(value.getId())) {

                String twoYear = DateUtil.addMonths(now, FundDayCount.TWO_YEARS.getCount(), DATE_FORMAT2);
                List<Aindexeodprices> aindexeodprices = aindexeodpricesMapper
                        .selectByWindCode(windCode, twoYear, now);
                SH300Map.put(value.getId(), HS_300(aindexeodprices));
            } else if (FundDayCount.THREE_YEARS.getId().equals(value.getId())) {

                String threeYear = DateUtil.addMonths(now, FundDayCount.THREE_MONTHS.getCount(), DATE_FORMAT2);
                List<Aindexeodprices> aindexeodprices = aindexeodpricesMapper
                        .selectByWindCode(windCode, threeYear, now);
                SH300Map.put(value.getId(), HS_300(aindexeodprices));
            } else if (FundDayCount.FIVE_YEARS.getId().equals(value.getId())) {

                String fiveYear = DateUtil.addMonths(now, FundDayCount.FIVE_YEARS.getCount(), DATE_FORMAT2);
                List<Aindexeodprices> aindexeodprices = aindexeodpricesMapper
                        .selectByWindCode(windCode, fiveYear, now);
                SH300Map.put(value.getId(), HS_300(aindexeodprices));
            }
        }
        SH300List.add(SH300Map);
        return SH300List;
    }
    //深沪300区间计算
    public BigDecimal HS_300(List<Aindexeodprices> aindexeodprices) {

        BigDecimal bigDecimal = Optional.ofNullable(aindexeodprices).
                map(item -> {
                    BigDecimal startsDqClose = item.stream().findFirst().get().getSDqClose();
                    BigDecimal endsDqClose = item.get(aindexeodprices.size() - 1).getSDqClose();
                    BigDecimal multiply = endsDqClose.subtract(startsDqClose)
                            .divide(startsDqClose, Constant.SCALE, BigDecimal.ROUND_HALF_UP)
                            .multiply(new BigDecimal("100"));
                    return multiply;
                }).
                orElse(new BigDecimal("0"));


        return bigDecimal;
    }
    //同类排名
    public List<Map<String, ?>> addSimilarRankingList(Chinamfperformance chinamfperformance) {

        List<Map<String, ?>> similarRankingList = new ArrayList<>();
        Map<String, BigDecimal> similarRankingMap = new HashMap<>();
        FundDayCount[] values = FundDayCount.values();
        for (FundDayCount value :
                values) {
            if (FundDayCount.ONE_WEEK.getId().equals(value.getId())) {
                similarRankingMap.put(value.getId(), chinamfperformance.getFSfrankRecentweek());
            } else if (FundDayCount.ONE_MONTH.getId().equals(value.getId())) {
                similarRankingMap.put(value.getId(), chinamfperformance.getFSfrankRecentmonth());
            } else if (FundDayCount.THREE_MONTHS.getId().equals(value.getId())) {
                similarRankingMap.put(value.getId(), chinamfperformance.getFSfrankRecentquarter());
            } else if (FundDayCount.SIX_MONTHS.getId().equals(value.getId())) {
                similarRankingMap.put(value.getId(), chinamfperformance.getFSfrankRecenthalfyear());
            } else if (FundDayCount.SINCE_THIS_YEAR.getId().equals(value.getId())) {
                similarRankingMap.put(value.getId(), chinamfperformance.getFSfrankThisyear());
            } else if (FundDayCount.SINCE_THIS_ESTABLISH.getId().equals(value.getId())) {
                similarRankingMap.put(value.getId(), chinamfperformance.getFSfrankSincefound());
            } else if (FundDayCount.ONE_YEAR.getId().equals(value.getId())) {
                similarRankingMap.put(value.getId(), chinamfperformance.getFSfrankRecentyear());
            } else if (FundDayCount.TWO_YEARS.getId().equals(value.getId())) {
                similarRankingMap.put(value.getId(), chinamfperformance.getFSfrankRecenttwoyear());
            } else if (FundDayCount.THREE_YEARS.getId().equals(value.getId())) {
                similarRankingMap.put(value.getId(), chinamfperformance.getFSfrankRecentthreeyear());
            } else if (FundDayCount.FIVE_YEARS.getId().equals(value.getId())) {
                similarRankingMap.put(value.getId(), chinamfperformance.getFSfrankRecentfiveyear());
            }
            similarRankingList.add(similarRankingMap);
        }
        return similarRankingList;
    }
    //同类排名变动
    public List<Map<String, ?>> addSimilarChangeList(Chinamfperformance chinamfperformance,Chinamfperformance chinamfperformanceNew) {

        List<Map<String, ?>> similarChangeList = new ArrayList<>();
        Map<String, BigDecimal> similarChangeMap = new HashMap<>();
        FundDayCount[] values = FundDayCount.values();
        for (FundDayCount value :
                values) {
            if (FundDayCount.ONE_WEEK.getId().equals(value.getId())) {
                similarChangeMap.put(value.getId(), chinamfperformance.getFSfrankRecentweek()
                        .subtract(chinamfperformanceNew.getFSfreturnRecentweek()));
            } else if (FundDayCount.ONE_MONTH.getId().equals(value.getId())) {
                similarChangeMap.put(value.getId(), chinamfperformance.getFSfrankRecentmonth()
                        .subtract(chinamfperformanceNew.getFSfrankRecentmonth()));
            } else if (FundDayCount.THREE_MONTHS.getId().equals(value.getId())) {
                similarChangeMap.put(value.getId(), chinamfperformance.getFSfrankRecentquarter()
                        .subtract(chinamfperformanceNew.getFSfrankRecentquarter()));
            } else if (FundDayCount.SIX_MONTHS.getId().equals(value.getId())) {
                similarChangeMap.put(value.getId(), chinamfperformance.getFSfrankRecenthalfyear()
                        .subtract(chinamfperformanceNew.getFSfrankRecenthalfyear()));
            } else if (FundDayCount.SINCE_THIS_YEAR.getId().equals(value.getId())) {
                similarChangeMap.put(value.getId(), chinamfperformance.getFSfrankThisyear()
                        .subtract(chinamfperformanceNew.getFSfrankThisyear()));
            } else if (FundDayCount.SINCE_THIS_ESTABLISH.getId().equals(value.getId())) {
                similarChangeMap.put(value.getId(), chinamfperformance.getFSfrankSincefound()
                        .subtract(chinamfperformanceNew.getFSfrankSincefound()));
            } else if (FundDayCount.ONE_YEAR.getId().equals(value.getId())) {
                similarChangeMap.put(value.getId(), chinamfperformance.getFSfrankRecentyear()
                        .subtract(chinamfperformanceNew.getFSfrankRecentyear()));
            } else if (FundDayCount.TWO_YEARS.getId().equals(value.getId())) {
                similarChangeMap.put(value.getId(), chinamfperformance.getFSfrankRecenttwoyear()
                        .subtract(chinamfperformanceNew.getFSfrankRecenttwoyear()));
            } else if (FundDayCount.THREE_YEARS.getId().equals(value.getId())) {
                similarChangeMap.put(value.getId(), chinamfperformance.getFSfrankRecentthreeyear()
                        .subtract(chinamfperformanceNew.getFSfrankRecentthreeyear()));
            } else if (FundDayCount.FIVE_YEARS.getId().equals(value.getId())) {
                similarChangeMap.put(value.getId(), chinamfperformance.getFSfrankRecentfiveyear()
                        .subtract(chinamfperformanceNew.getFSfrankRecentfiveyear()));
            }
            similarChangeList.add(similarChangeMap);
        }
        return similarChangeList;
    }
    //三分位排名变动
    public List<Map<String, ?>> addThirdRankingList(Chinamfperformance chinamfperformance) {

        List<Map<String, ?>> thirdRankingList = new ArrayList<>();
        Map<String, String> thirdRankingMap = new HashMap<>();
        FundDayCount[] values = FundDayCount.values();
        for (FundDayCount value :
                values) {
            if (FundDayCount.ONE_WEEK.getId().equals(value.getId())) {
                thirdRankingMap.put(value.getId(),thirdRanking(String.valueOf(chinamfperformance.getFSfrankRecentweek())));
            } else if (FundDayCount.ONE_MONTH.getId().equals(value.getId())) {
                thirdRankingMap.put(value.getId(),thirdRanking(String.valueOf(chinamfperformance.getFSfrankRecentmonth())));
            } else if (FundDayCount.THREE_MONTHS.getId().equals(value.getId())) {
                thirdRankingMap.put(value.getId(),thirdRanking(String.valueOf(chinamfperformance.getFSfrankRecentquarter())));
            } else if (FundDayCount.SIX_MONTHS.getId().equals(value.getId())) {
                thirdRankingMap.put(value.getId(),thirdRanking(String.valueOf(chinamfperformance.getFSfrankRecenthalfyear())));
            } else if (FundDayCount.SINCE_THIS_YEAR.getId().equals(value.getId())) {
                thirdRankingMap.put(value.getId(),thirdRanking(String.valueOf(chinamfperformance.getFSfrankThisyear())));
            } else if (FundDayCount.SINCE_THIS_ESTABLISH.getId().equals(value.getId())) {
                thirdRankingMap.put(value.getId(),thirdRanking(String.valueOf(chinamfperformance.getFSfrankSincefound())));
            } else if (FundDayCount.ONE_YEAR.getId().equals(value.getId())) {
                thirdRankingMap.put(value.getId(),thirdRanking(String.valueOf(chinamfperformance.getFSfrankRecentyear())));
            } else if (FundDayCount.TWO_YEARS.getId().equals(value.getId())) {
                thirdRankingMap.put(value.getId(),thirdRanking(String.valueOf(chinamfperformance.getFSfrankRecenttwoyear())));
            } else if (FundDayCount.THREE_YEARS.getId().equals(value.getId())) {
                thirdRankingMap.put(value.getId(),thirdRanking(String.valueOf(chinamfperformance.getFSfrankRecentthreeyear())));
            } else if (FundDayCount.FIVE_YEARS.getId().equals(value.getId())) {
                thirdRankingMap.put(value.getId(),thirdRanking(String.valueOf(chinamfperformance.getFSfrankRecentfiveyear())));
            }
            thirdRankingList.add(thirdRankingMap);
        }
        return thirdRankingList;
    }
    public String thirdRanking(String ranking) {
        String quantileThrees = Optional.ofNullable(ranking)
                .map(s -> {
                    BigDecimal quantileThree = null;
                    String[] averageArr = s.split("/");
                    double quantileOne = Double.parseDouble("1") / Double.parseDouble("3");
                    double quantileTwo = Double.parseDouble("2") / Double.parseDouble("3");

                    double three = Double.parseDouble(String.valueOf(new BigDecimal(averageArr[0])
                            .divide(new BigDecimal((averageArr[1])), 2, BigDecimal.ROUND_HALF_UP)));
                    if (three <= quantileOne) {
                        quantileThree = new BigDecimal(String.valueOf(1));
                    } else if (three <= quantileTwo && quantileOne < three) {
                        quantileThree = new BigDecimal(String.valueOf(2));
                    } else if (three > quantileTwo) {
                        quantileThree = new BigDecimal(String.valueOf(3));
                    }
                    return String.valueOf(quantileThree);
                }).orElse("0");

        return quantileThrees;
    }
}