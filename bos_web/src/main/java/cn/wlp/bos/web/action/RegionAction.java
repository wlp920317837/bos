package cn.wlp.bos.web.action;

import cn.wlp.bos.common.PinYin4jUtils;
import cn.wlp.bos.domain.Region;
import cn.wlp.bos.service.RegionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-06 19:44
 * 区域action
 **/
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

    private File regionFile;
    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    @Autowired
    private RegionService regionService;

    public String importXls() throws Exception {
        List<Region> regionList = new ArrayList<Region>();
        //使用POI解析Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
        //根据名称获得指定Sheet对象
        HSSFSheet hssfSheet = workbook.getSheet("Sheet1");
        for (Row row : hssfSheet) {
            int rowNum = row.getRowNum();
            if (rowNum == 0) {
                continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            //包装一个区域对象
            Region region = new Region(id, province, city, district, postcode, null, null, null);

            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            district = district.substring(0, district.length() - 1);
            String info = province + city + district;
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            String shortcode = StringUtils.join(headByString);
            //城市编码---->>shijiazhuang
            String citycode = PinYin4jUtils.hanziToPinyin(city, "");

            region.setShortcode(shortcode);
            region.setCitycode(citycode);
            regionList.add(region);
        }
        //批量保存
        regionService.saveBatch(regionList);
        return NONE;
    }

    public String pageQuery() {
        regionService.pageQuery(pageBean);
        java2Json(pageBean, new String[]{"page", "pageSize", "subareas"});
        return null;
    }

    public String add() {
        String province = model.getProvince();
        String city = model.getCity();
        String district = model.getDistrict();
        province = province.substring(0, province.length() - 1);
        city = city.substring(0, city.length() - 1);
        district = district.substring(0, district.length() - 1);
        String info = province + city + district;
        String[] headByString = PinYin4jUtils.getHeadByString(info);
        String shortcode = StringUtils.join(headByString);
        //城市编码---->>shijiazhuang
        String citycode = PinYin4jUtils.hanziToPinyin(city, "");
        model.setShortcode(shortcode);
        model.setCitycode(citycode);

        model.setId(shortcode + 1);
        regionService.add(model);
        return LIST;
    }

    public String edit() {
        String province = model.getProvince();
        String city = model.getCity();
        String district = model.getDistrict();
        province = province.substring(0, province.length() - 1);
        city = city.substring(0, city.length() - 1);
        district = district.substring(0, district.length() - 1);
        String info = province + city + district;
        String[] headByString = PinYin4jUtils.getHeadByString(info);
        String shortcode = StringUtils.join(headByString);
        //城市编码---->>shijiazhuang
        String citycode = PinYin4jUtils.hanziToPinyin(city, "");
        model.setShortcode(shortcode);
        model.setCitycode(citycode);

        regionService.edit(model);
        return LIST;
    }

    public String deleteBatch() {
        regionService.deleteBatch(ids);
        return LIST;
    }

    private String q;

    public void setQ(String q) {
        this.q = q;
    }

    public String listajax() {
        List<Region> list = null;
        if (StringUtils.isNotBlank(q)) {
            list = regionService.findByQ(q);
        } else {
            list = regionService.findAll();
        }
        java2Json(list, new String[]{"subareas"});
        return null;
    }
}
