package com.shangpin.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 发现管理
 * 
 * @author 李灵
 * 
 */
@Controller
@RequestMapping("/management/maintain/find")
public class FindController {
//    @Autowired
//    private FindService findService;
//
//    @Autowired
//    private ShangPinService shangPinService;
//
//    private static final String LIST = "management/maintain/find/list";
//    private static final String ACTIVITYLIST = "management/maintain/find/activity_list";
//    private static final String CREATE = "management/maintain/find/create";
//    private static final String UPDATE = "management/maintain/find/update";
//    private static final String SETSORT = "management/maintain/find/setsort";
//
//    @InitBinder
//    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder, WebDataBinder dataBinder) throws Exception {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
//    }
//
//    @RequiresPermissions("Find:view")
//    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
//    public String list(Page page, HttpServletRequest request, Map<String, Object> map) {
//        Specification<Find> specification = DynamicSpecifications.bySearchFilter(request, Find.class);
//        page.setOrderField("sortBy");
//        page.setOrderDirection(page.ORDER_DIRECTION_ASC);
//        ;
//        List<Find> finds = findService.findByExample(specification, page);
//        List<Find> findList = new ArrayList<Find>();
//        for (int i = 0; i < finds.size(); i++) {
//            Find find = finds.get(i);
//
//            if (find.getType().equals(Type.ACTIVITY)) {
//                if (StringUtils.isNotEmpty(find.getIphonePic())) {
//                    find.setImgUrl(find.getIphonePic());
//                }
//            }
//            if (StringUtils.isNotEmpty(find.getImgUrl())) {
//                find.setImgUrl(find.getImgUrl().replace("-{w}-{h}", "-180-110"));
//
//            }
//            findList.add(find);
//
//        }
//        map.put("page", page);
//        map.put("finds", findList);
//        return LIST;
//    }
//
//    @RequiresPermissions("Find:view")
//    @RequestMapping(value = "/activityList", method = { RequestMethod.GET, RequestMethod.POST })
//    public String activityList(Page page, HttpServletRequest request, Map<String, Object> map) {
//        String keyword = request.getParameter("keyword");
//        String startTime = request.getParameter("startTime");
//        String endTime = request.getParameter("endTime");
//        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        List<Find> finds = new ArrayList<Find>();
//        List<ActivityOfMain> activityOfMainList = new ArrayList<ActivityOfMain>();
//        try {
//            if (keyword != null && !keyword.equals("")) {
//                activityOfMainList = shangPinService.findSearchSubjectList(keyword);
//            }
//            if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
//                activityOfMainList = shangPinService.findSubjectListByTime(startTime, endTime);
//            }
//
//            if (activityOfMainList != null) {
//                for (int i = 0; i < activityOfMainList.size(); i++) {
//                    ActivityOfMain activityOfMain = activityOfMainList.get(i);
//                    Find find = new Find();
//                    find.setActivityId(activityOfMain.getId());
//                    find.setDescription(activityOfMain.getDesc());
//                    if (StringUtils.isNotEmpty(activityOfMain.getEndtime())) {
//                        find.setEndTime(sdf.parse(activityOfMain.getEndtime()));
//                    }
//                    if (StringUtils.isNotEmpty(activityOfMain.getStarttime())) {
//                        find.setStartTime(sdf.parse(activityOfMain.getStarttime()));
//                    }
//                    find.setIphonePic(activityOfMain.getIphonepic());
//                    find.setMobilePic(activityOfMain.getMobilepic());
//                    if (StringUtils.isNotEmpty(activityOfMain.getPretime())) {
//                        find.setPreTime(sdf.parse(activityOfMain.getPretime()));
//                    }
//                    find.setTitle(activityOfMain.getName());
//                    find.setShareUrl(activityOfMain.getShareurl());
//                    find.setStatus(activityOfMain.getStatus());
//                    find.setSubTitle(activityOfMain.getSubtitle());
//                    finds.add(find);
//
//                }
//                map.put("activityFinds", finds);
//                return ACTIVITYLIST;
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        map.put("activityFinds", finds);
//        return ACTIVITYLIST;
//
//    }
//
//    @Log(message = "添加了{0}活动。")
//    @RequiresPermissions("Find:save")
//    @RequestMapping(value = "/create", method = { RequestMethod.POST })
//    public @ResponseBody
//    String create(@Valid Find find) throws ParseException {
//        find.setCreateTime(new Date());
//        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            if (find.getType().equals(Type.ACTIVITY)) {
//
//                Long count = findService.isExist(sdf.parse(sdf.format(new Date())), find.getActivityId());
//                if (count > 0) {
//                    return AjaxObject.newError("活动已经配置，无需重复配置！").toString();
//                }
//                if (find.getStartTime() != null) {
//                    find.setShowStartTime(find.getStartTime());
//                }
//                if (find.getEndTime() != null) {
//                    find.setShowEndTime(find.getEndTime());
//                }
//                if (find.getPreTime() != null) {
//                    find.setMobilePreTime(find.getPreTime());
//                }
//            }
//            find.setSortBy(1);
//            findService.save(find);
//            String minSort = findService.findMinSort(sdf.parse(sdf.format(new Date())), find.getId());
//            if (StringUtils.isNotEmpty(minSort)) {
//                int mincount = Integer.valueOf(minSort.split("[.]")[0]);
//                if (mincount >= 1) {
//                    findService.sortRetrude(sdf.parse(sdf.format(new Date())), find.getId());
//                }
//            }
//        } catch (ExistedException e) {
//            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
//        }
//        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { find.getTitle() }));
//        return AjaxObject.newOk("添加成功！").toString();
//    }
//
//    @RequiresPermissions("Find:save")
//    @RequestMapping(value = "/preCreate", method = RequestMethod.GET)
//    public String preCreate() {
//        return CREATE;
//    }
//
//    @RequiresPermissions("Find:edit")
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
//    public String update(@PathVariable Long id, Map<String, Object> map) {
//        Find find = findService.get(id);
//        if (find.getType().equals(Type.ACTIVITY)) {
//            find.setImgUrl(find.getIphonePic().replace("-{w}-{h}", "-180-110"));
//
//        }
//        map.put("find", find);
//        return UPDATE;
//    }
//
//    @Log(message = "修改了{0}活动的信息。")
//    @RequiresPermissions("Find:edit")
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public @ResponseBody
//    String update(@Valid @ModelAttribute("preloadFind") Find find) {
//        if (StringUtils.isNotEmpty(find.getImgUrl())) {
//            find.setImgUrl(find.getImgUrl().replace("-180-110", "-{w}-{h}"));
//        }
//
//        findService.update(find);
//        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { find.getTitle() }));
//        return AjaxObject.newOk("修改成功！").toString();
//    }
//
//    @Log(message = "删除了{0}活动。")
//    @RequiresPermissions("Find:delete")
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    public @ResponseBody
//    String deleteMany(Long[] ids) {
//        String[] brandnames = new String[ids.length];
//        try {
//            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            for (int i = 0; i < ids.length; i++) {
//                Find find = findService.get(ids[i]);
//                findService.delete(find.getId());
//
//                findService.retrudeSortDel(sdf.parse(sdf.format(new Date())), find.getSortBy());
//                LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { find.getTitle() }));
//            }
//        } catch (Exception e) {
//            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
//        }
//
//        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { Arrays.toString(brandnames) }));
//        return AjaxObject.newOk("删除成功！").setCallbackType("").toString();
//    }
//
//    @RequiresPermissions("Find:edit")
//    @RequestMapping(value = "/preSetSort/{id}", method = RequestMethod.GET)
//    public String preSetSort(@PathVariable Long id, Map<String, Object> map) {
//
//        map.put("id", id);
//        return SETSORT;
//    }
//
//    @Log(message = "修改了{0}静态活动的信息。")
//    @RequiresPermissions("Find:edit")
//    @RequestMapping(value = "/setSort", method = RequestMethod.POST)
//    public @ResponseBody
//    String setSort(@Valid @ModelAttribute("preloadFind") Find find) {
//        try {
//            Find model = findService.get(find.getId());
//            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            int sort = findService.findMaxSort(sdf.parse(sdf.format(new Date())), find.getId());
//            if (find.getSortBy() < 1) {
//                return AjaxObject.newError("位置不能小于1！").toString();
//            }
//            if (sort < find.getSortBy()) {
//                return AjaxObject.newError("当前可设置的最大位置为！" + sort).toString();
//
//            }
//
//            int con = findService.findBySort(sdf.parse(sdf.format(new Date())), model.getSortBy());
//            if (model.getSortBy() > find.getSortBy()) {
//
//                if (con > 0) {
//                    findService.retrudeByEndSort(sdf.parse(sdf.format(new Date())), model.getSortBy());
//                }
//
//            } else {
//
//                if (con > 0) {
//                    findService.retrudeByStartSort(sdf.parse(sdf.format(new Date())), model.getSortBy());
//                }
//
//            }
//            model.setSortBy(find.getSortBy());
//            findService.update(model);
//            LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { find.getTitle() }));
//            return AjaxObject.newOk("修改成功！").toString();
//        } catch (Exception e) {
//            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
//        }
//    }
//
//    /**
//     * 图片上传
//     * 
//     * @param request
//     * @param file
//     * @param map
//     * @return
//     */
//    @RequiresPermissions("Find:upload")
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public @ResponseBody
//    String uploadImg(HttpServletRequest request, @RequestParam MultipartFile file, Map<String, Object> map) {
//        String imgUrl = "";
//        try {
//            // 对图片服务器返回json进行解析
//            String json = FileUtils.uploadImg2server(file);
//            System.out.println("图片服务器返回JSON：" + json);
//
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode rootNode = mapper.readTree(json);
//
//            imgUrl = rootNode.path("content").path("PicUrl").asText();
//            String image = imgUrl.replace("{w}", "180").replace("{h}", "110");
//            System.out.println(image);
//            // imgUrl = image;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return AjaxObject.newOk(imgUrl).toString();
//    }

}