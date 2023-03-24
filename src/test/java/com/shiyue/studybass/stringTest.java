package com.shiyue.studybass;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

public class stringTest {


    String s = "t_course_section_study_log_cm\n" +
            "t_course_section_study_log_bj\n" +
            "t_course_section_study_log_tj\n" +
            "t_course_section_study_log_hb\n" +
            "t_course_section_study_log_sx\n" +
            "t_course_section_study_log_nm\n" +
            "t_course_section_study_log_ln\n" +
            "t_course_section_study_log_jl\n" +
            "t_course_section_study_log_hl\n" +
            "t_course_section_study_log_sh\n" +
            "t_course_section_study_log_js\n" +
            "t_course_section_study_log_zj\n" +
            "t_course_section_study_log_fj\n" +
            "t_course_section_study_log_ah\n" +
            "t_course_section_study_log_jx\n" +
            "t_course_section_study_log_sd\n" +
            "t_course_section_study_log_hn\n" +
            "t_course_section_study_log_eb\n" +
            "t_course_section_study_log_xn\n" +
            "t_course_section_study_log_gd\n" +
            "t_course_section_study_log_gx\n" +
            "t_course_section_study_log_qo\n" +
            "t_course_section_study_log_cq\n" +
            "t_course_section_study_log_sc\n" +
            "t_course_section_study_log_gz\n" +
            "t_course_section_study_log_yn\n" +
            "t_course_section_study_log_xz\n" +
            "t_course_section_study_log_sn\n" +
            "t_course_section_study_log_gs\n" +
            "t_course_section_study_log_qh\n" +
            "t_course_section_study_log_nx\n" +
            "t_course_section_study_log_xj\n" +
            "t_course_section_study_log_zgtt\n" +
            "t_course_section_study_log_zx\n" +
            "t_course_section_study_log_other\n";



    public static void main(String[] args) {
        String sql = "create table t_course_section_study_progress\n" +
                "(\n" +
                "    f_id               varchar(40)  not null\n" +
                "        primary key,\n" +
                "    f_member_id        varchar(40)  null comment '用户ID',\n" +
                "    f_course_id        varchar(40)  null comment '课程ID',\n" +
                "    f_section_id       varchar(40)  null comment '课程节ID',\n" +
                "    f_begin_time       bigint       null comment '学习开始时间',\n" +
                "    f_finish_status    int(1)       null comment '学习状态，0-未开始，1-学习中，2-已完成，3-已放弃，4-标记完成，5-待审核，6-审核未通过，7-待评卷',\n" +
                "    f_finish_time      bigint       null comment '完成时间',\n" +
                "    f_completed_rate   int          null comment '完成进度(百分比)',\n" +
                "    f_study_total_time int          null comment '学习累计时长，单位秒',\n" +
                "    f_last_access_time bigint       null comment '最后访问时间',\n" +
                "    f_exam_status      int(1)       null comment '考试状态 0 未通过 1通过 2待评卷 3.待考试',\n" +
                "    f_lesson_location  varchar(200) null comment '最后学习退出的位置',\n" +
                "    f_create_time      bigint       null,\n" +
                "    f_commit_time      bigint       null comment '提交时间',\n" +
                "    f_submit_text      text         null comment '提交内容',\n" +
                "    f_audit_member_id  varchar(40)  null comment '审核人ID',\n" +
                "    f_score            int          null comment '评分，用于存储考试、评估、作业评分',\n" +
                "    f_comments         text         null comment '作业审核评语',\n" +
                "    f_audit_pass       int(1)       null comment '作业审核是否通过，1-通过；2-打回重新提交',\n" +
                "    f_visits           int(20)      null comment '访问知识的次数（当前章节为知识时使用）',\n" +
                "    constraint unique_t_course_section_p_member_section\n" +
                "        unique (f_member_id, f_section_id)\n" +
                ")\n" +
                "    comment '课程节学习进度';\n" +
                "\n" +
                "create index idx_t_course_section_study_progress_cmt\n" +
                "    on t_course_section_study_progress (f_course_id, f_member_id, f_last_access_time);\n" +
                "\n" +
                "create index idx_t_course_section_study_progress_f_course_id\n" +
                "    on t_course_section_study_progress (f_course_id);\n" +
                "\n" +
                "create index idx_t_course_section_study_progress_f_last_access_time\n" +
                "    on t_course_section_study_progress (f_last_access_time);\n" +
                "\n" +
                "create index idx_t_course_section_study_progress_f_section_id\n" +
                "    on t_course_section_study_progress (f_section_id);\n" +
                "\n" +
                "create index idx_t_course_section_study_progress_ms\n" +
                "    on t_course_section_study_progress (f_member_id, f_section_id);";
        try {
            FileReader fileReader = new FileReader("D:/name.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
//            FileReader fileReaderSql = new FileReader("D:\\sql");
//            BufferedReader bufferedReaderSql = new BufferedReader(fileReaderSql);
//            String lineSql = "";
            StringBuilder stringBuilder = new StringBuilder("");
            Set<String> set = new HashSet();
            while ((line = bufferedReader.readLine())!=null){
                if (line.length()!=0){
                    set.add(line);
                }
                System.out.println(line);
            }
            System.out.println(set.size());
            for (String str :set){
                String a = sql.replace("course_section_study_progress", "course_section_study_progress_" + str.substring(27));
                String c = a.replace("课程节学习进度", "课程节学习进度" + str.substring(27));
                String d = c.replace("table t_course_section_study_progress", ("table `course-study`.t_course_section_study_progress"));
                String e = d.replace("on t_course_section_study_progress", ("on `course-study`.t_course_section_study_progress"));
                System.out.println("t_course_section_study_progress_" + str.substring(27));

                stringBuilder.append(e);
                stringBuilder.append("\n");
                stringBuilder.append("#---------------------------------------------------------------------------------------");
                stringBuilder.append("\n");
            }
            System.out.println(stringBuilder.toString());
            FileOutputStream fos = new FileOutputStream("D:/sql.sql");
            fos.write(stringBuilder.toString().getBytes());
            fos.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }


    String tableName = "t_course_section_study_progress_nx\n" +
            "t_course_section_study_progress_bj\n" +
            "t_course_section_study_progress_js\n" +
            "t_course_section_study_progress_hn\n" +
            "t_course_section_study_progress_hl\n" +
            "t_course_section_study_progress_fj\n" +
            "t_course_section_study_progress_ln\n" +
            "t_course_section_study_progress_jl\n" +
            "t_course_section_study_progress_xz\n" +
            "t_course_section_study_progress_zx\n" +
            "t_course_section_study_progress_nm\n" +
            "t_course_section_study_progress_sn\n" +
            "t_course_section_study_progress_other\n" +
            "t_course_section_study_progress_qh\n" +
            "t_course_section_study_progress_sh\n" +
            "t_course_section_study_progress_yn\n" +
            "t_course_section_study_progress_sc\n" +
            "t_course_section_study_progress_sd\n" +
            "t_course_section_study_progress_jx\n" +
            "t_course_section_study_progress_cm\n" +
            "t_course_section_study_progress_ah\n" +
            "t_course_section_study_progress_sx\n" +
            "t_course_section_study_progress_zgtt\n" +
            "t_course_section_study_progress_qo\n" +
            "t_course_section_study_progress_eb\n" +
            "t_course_section_study_progress_gd\n" +
            "t_course_section_study_progress_hb\n" +
            "t_course_section_study_progress_tj\n" +
            "t_course_section_study_progress_xn\n" +
            "t_course_section_study_progress_xj\n" +
            "t_course_section_study_progress_gz\n" +
            "t_course_section_study_progress_zj\n" +
            "t_course_section_study_progress_gx\n" +
            "t_course_section_study_progress_cq\n" +
            "t_course_section_study_progress_gs";

    String log = "96531,t_course_section_study_log_cm\n" +
            "172712787,t_course_section_study_log_other\n" +
            "152603244,t_course_section_study_log_other\n" +
            "147435546,t_course_section_study_log_other\n" +
            "155132038,t_course_section_study_log_other\n" +
            "110918047,t_course_section_study_log_other\n" +
            "45229480,t_course_section_study_log_other\n" +
            "172762630,t_course_section_study_log_zx\n" +
            "184091188,t_course_section_study_log_zgtt\n" +
            "1062701,t_course_section_study_log_xj\n" +
            "1012774,t_course_section_study_log_nx\n" +
            "1100282,t_course_section_study_log_qh\n" +
            "156426232,t_course_section_study_log_other\n" +
            "156426227,t_course_section_study_log_other\n" +
            "168524524,t_course_section_study_log_other\n" +
            "10000002,t_course_section_study_log_other\n" +
            "175152245,t_course_section_study_log_other\n" +
            "170594644,t_course_section_study_log_other\n" +
            "155242868,t_course_section_study_log_other\n" +
            "140075687,t_course_section_study_log_other\n" +
            "79495171,t_course_section_study_log_other\n" +
            "96597,t_course_section_study_log_other\n" +
            "96674,t_course_section_study_log_other\n" +
            "3770796,t_course_section_study_log_other\n" +
            "7347731c-139b-443b-9a89-14a1c20a0421,t_course_section_study_log_other\n" +
            "170630482,t_course_section_study_log_other\n" +
            "1322085,t_course_section_study_log_gs\n" +
            "1012970,t_course_section_study_log_sn\n" +
            "1133482,t_course_section_study_log_xz\n" +
            "80307,t_course_section_study_log_zj\n" +
            "1148390,t_course_section_study_log_js\n" +
            "1754249,t_course_section_study_log_sh\n" +
            "44261,t_course_section_study_log_hl\n" +
            "1073360,t_course_section_study_log_jl\n" +
            "1061451,t_course_section_study_log_ln\n" +
            "1093623,t_course_section_study_log_nm\n" +
            "98209,t_course_section_study_log_sx\n" +
            "1011203,t_course_section_study_log_hb\n" +
            "1567690,t_course_section_study_log_tj\n" +
            "1105092,t_course_section_study_log_bj\n" +
            "96493,t_course_section_study_log_cm\n" +
            "1093072,t_course_section_study_log_fj\n" +
            "552512,t_course_section_study_log_ah\n" +
            "1094794,t_course_section_study_log_yn\n" +
            "1495590,t_course_section_study_log_gz\n" +
            "1495996,t_course_section_study_log_sc\n" +
            "1095592,t_course_section_study_log_cq\n" +
            "1013550,t_course_section_study_log_qo\n" +
            "993478,t_course_section_study_log_gx\n" +
            "1073923,t_course_section_study_log_gd\n" +
            "105408,t_course_section_study_log_xn\n" +
            "1018307,t_course_section_study_log_eb\n" +
            "55836,t_course_section_study_log_hn\n" +
            "1180849,t_course_section_study_log_sd\n" +
            "567498,t_course_section_study_log_jx\n";


    @Test
    public void getConfig() throws Exception{
        String[] lines = log.split("\\r?\\n");
        System.out.println(lines.length);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s:lines){
            String[] line = s.split(",");
            stringBuilder.append("INSERT INTO `course-study`.t_split_table_config (f_id, f_organization_id, f_source, f_target_table, f_create_time) VALUES (");
            stringBuilder.append("'"+java.util.UUID.randomUUID().toString()+"',");
            stringBuilder.append("'"+line[0]+"',");
            stringBuilder.append("7,");
            stringBuilder.append("'"+line[1].replace("t_course_section_study_log_","t_course_section_study_progress_")+"',");
            stringBuilder.append("unix_timestamp(now())*1000);");
            stringBuilder.append("\n");
        }
        FileOutputStream fos = new FileOutputStream("D:/ddl.sql");
        fos.write(stringBuilder.toString().getBytes());
        fos.close();
    }


    @Test
    public void exaption(){
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(1);
//        for (Integer i : list){
//            list.remove(i);
//        }
        List<Integer> list1 = list.subList(0,1);
        List<Integer> list2 = Arrays.asList(1,2,3);
//        list2.add(4);
        System.out.println(list1.add(3));

        int[] a = {1,2,3};
        System.out.println(Arrays.asList(a));

    }


    String str123 = "f549b2a2-7b43-31a2-b081-70e77654cb2d\n" +
            "69a3a82e-3683-3889-9734-239cb955b93f\n";


    @Test
    public void getConfig1() throws Exception{
        String[] lines = str123.split("\\r?\\n");
        System.out.println(lines.length);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s:lines){
            stringBuilder.append("INSERT INTO `course-study`.t_audience_member (f_id, f_item_id, f_member_id, f_create_time) VALUES (");
            stringBuilder.append("'"+java.util.UUID.randomUUID().toString()+"',");
            stringBuilder.append("'8d1e40b3-c205-45b4-a24f-bfab33ea931c',");
            stringBuilder.append("'"+s+"',");
            stringBuilder.append("unix_timestamp(now())*1000);");
            stringBuilder.append("\n");
        }
        FileOutputStream fos = new FileOutputStream("D:/ddl.sql");
        fos.write(stringBuilder.toString().getBytes());
        fos.close();
    }


    @Test
    public void sadwatttt() throws Exception{
        String a = "t_course_section_study_log_ah_day\n" +
                "t_course_section_study_log_bj_day\n" +
                "t_course_section_study_log_cm_day\n" +
                "t_course_section_study_log_cq_day\n" +
                "t_course_section_study_log_eb_day\n" +
                "t_course_section_study_log_fj_day\n" +
                "t_course_section_study_log_gd_day\n" +
                "t_course_section_study_log_gs_day\n" +
                "t_course_section_study_log_gx_day\n" +
                "t_course_section_study_log_gz_day\n" +
                "t_course_section_study_log_hb_day\n" +
                "t_course_section_study_log_hl_day\n" +
                "t_course_section_study_log_hn_day\n" +
                "t_course_section_study_log_jl_day\n" +
                "t_course_section_study_log_js_day\n" +
                "t_course_section_study_log_jx_day\n" +
                "t_course_section_study_log_ln_day\n" +
                "t_course_section_study_log_nm_day\n" +
                "t_course_section_study_log_nx_day\n" +
                "t_course_section_study_log_other_day\n" +
                "t_course_section_study_log_qh_day\n" +
                "t_course_section_study_log_qo_day\n" +
                "t_course_section_study_log_sc_day\n" +
                "t_course_section_study_log_sd_day\n" +
                "t_course_section_study_log_sh_day\n" +
                "t_course_section_study_log_sn_day\n" +
                "t_course_section_study_log_sx_day\n" +
                "t_course_section_study_log_tj_day\n" +
                "t_course_section_study_log_xj_day\n" +
                "t_course_section_study_log_xn_day\n" +
                "t_course_section_study_log_xz_day\n" +
                "t_course_section_study_log_yn_day\n" +
                "t_course_section_study_log_zgtt_day\n" +
                "t_course_section_study_log_zj_day\n" +
                "t_course_section_study_log_zx_day\n";

        String[] lines = a.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s: lines){
            stringBuilder.append("alter table ");
            stringBuilder.append(s);
            stringBuilder.append(" change t_click f_click int default 0 null comment '点击次数';");
            stringBuilder.append("\n");
        }

        FileOutputStream fos = new FileOutputStream("D:/ddl.sql");
        fos.write(stringBuilder.toString().getBytes());
        fos.close();

    }



    @Test
    public void sadwattttr() throws Exception{
        String a = "t_subject_study_log_ah_day\n" +
                "t_subject_study_log_bj_day\n" +
                "t_subject_study_log_cm_day\n" +
                "t_subject_study_log_cq_day\n" +
                "t_subject_study_log_eb_day\n" +
                "t_subject_study_log_fj_day\n" +
                "t_subject_study_log_gd_day\n" +
                "t_subject_study_log_gs_day\n" +
                "t_subject_study_log_gx_day\n" +
                "t_subject_study_log_gz_day\n" +
                "t_subject_study_log_hb_day\n" +
                "t_subject_study_log_hl_day\n" +
                "t_subject_study_log_hn_day\n" +
                "t_subject_study_log_jl_day\n" +
                "t_subject_study_log_js_day\n" +
                "t_subject_study_log_jx_day\n" +
                "t_subject_study_log_ln_day\n" +
                "t_subject_study_log_nm_day\n" +
                "t_subject_study_log_nx_day\n" +
                "t_subject_study_log_other_day\n" +
                "t_subject_study_log_qh_day\n" +
                "t_subject_study_log_qo_day\n" +
                "t_subject_study_log_sc_day\n" +
                "t_subject_study_log_sd_day\n" +
                "t_subject_study_log_sh_day\n" +
                "t_subject_study_log_sn_day\n" +
                "t_subject_study_log_sx_day\n" +
                "t_subject_study_log_tj_day\n" +
                "t_subject_study_log_xj_day\n" +
                "t_subject_study_log_xn_day\n" +
                "t_subject_study_log_xz_day\n" +
                "t_subject_study_log_yn_day\n" +
                "t_subject_study_log_zgtt_day\n" +
                "t_subject_study_log_zj_day\n" +
                "t_subject_study_log_zx_day\n";

        String[] lines = a.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s: lines){
            stringBuilder.append("alter table ");
            stringBuilder.append(s);
            stringBuilder.append(" change t_click f_click int default 0 null comment '点击次数';");
            stringBuilder.append("\n");
        }

        FileOutputStream fos = new FileOutputStream("D:/ddl.sql");
        fos.write(stringBuilder.toString().getBytes());
        fos.close();

    }


    @Test
    public void tostr() throws Exception{
        String a = "t_subject_study_log_ah_day\n" +
                "t_subject_study_log_bj_day\n" +
                "t_subject_study_log_cm_day\n" +
                "t_subject_study_log_cq_day\n" +
                "t_subject_study_log_eb_day\n" +
                "t_subject_study_log_fj_day\n" +
                "t_subject_study_log_gd_day\n" +
                "t_subject_study_log_gs_day\n" +
                "t_subject_study_log_gx_day\n" +
                "t_subject_study_log_gz_day\n" +
                "t_subject_study_log_hb_day\n" +
                "t_subject_study_log_hl_day\n" +
                "t_subject_study_log_hn_day\n" +
                "t_subject_study_log_jl_day\n" +
                "t_subject_study_log_js_day\n" +
                "t_subject_study_log_jx_day\n" +
                "t_subject_study_log_ln_day\n" +
                "t_subject_study_log_nm_day\n" +
                "t_subject_study_log_nx_day\n" +
                "t_subject_study_log_other_day\n" +
                "t_subject_study_log_qh_day\n" +
                "t_subject_study_log_qo_day\n" +
                "t_subject_study_log_sc_day\n" +
                "t_subject_study_log_sd_day\n" +
                "t_subject_study_log_sh_day\n" +
                "t_subject_study_log_sn_day\n" +
                "t_subject_study_log_sx_day\n" +
                "t_subject_study_log_tj_day\n" +
                "t_subject_study_log_xj_day\n" +
                "t_subject_study_log_xn_day\n" +
                "t_subject_study_log_xz_day\n" +
                "t_subject_study_log_yn_day\n" +
                "t_subject_study_log_zgtt_day\n" +
                "t_subject_study_log_zj_day\n" +
                "t_subject_study_log_zx_day\n";

        String[] lines = a.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s: lines){
            stringBuilder.append(s).append("|");
        }
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void sadstr() throws Exception{
        String a = "t_repository_course_info\n" +
                "t_repository_teacher_specialist\n" +
                "t_repository_dwell\n" +
                "t_repository_professional_emphasis\n" +
                "t_repository_project_lecturer\n" +
                "t_repository_seminar_room\n" +
                "t_repository_project\n" +
                "t_repository_supplier_photo\n" +
                "t_repository_business_attachment\n" +
                "t_repository_project_student\n" +
                "t_repository_students\n" +
                "t_repository_audience\n" +
                "t_repository_supplier\n" +
                "t_repository_supplier_tag\n" +
                "t_repository_tag\n" +
                "t_repository_project_supplier\n" +
                "t_repository_professional_teacher\n" +
                "t_repository_project_expend\n" +
                "t_repository_evaluate\n" +
                "t_repository_project_course\n" +
                "t_repository_project_photo\n";

        String[] lines = a.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s: lines){
            stringBuilder.append(s).append("|");
        }
        System.out.println(stringBuilder.toString());


    }




    @Test
    public void getSql() throws Exception {
        String sql = "t_course_study_progress\n" +
                "t_course_study_progress_zx\n" +
                "t_course_study_progress_zj\n" +
                "t_course_study_progress_zgtt\n" +
                "t_course_study_progress_yn\n" +
                "t_course_study_progress_xz\n" +
                "t_course_study_progress_xn\n" +
                "t_course_study_progress_xj\n" +
                "t_course_study_progress_tj\n" +
                "t_course_study_progress_sx\n" +
                "t_course_study_progress_sn\n" +
                "t_course_study_progress_sh\n" +
                "t_course_study_progress_sd\n" +
                "t_course_study_progress_sc\n" +
                "t_course_study_progress_qo\n" +
                "t_course_study_progress_qh\n" +
                "t_course_study_progress_other\n" +
                "t_course_study_progress_nx\n" +
                "t_course_study_progress_nm\n" +
                "t_course_study_progress_ln\n" +
                "t_course_study_progress_jx\n" +
                "t_course_study_progress_js\n" +
                "t_course_study_progress_jl\n" +
                "t_course_study_progress_hn\n" +
                "t_course_study_progress_hl\n" +
                "t_course_study_progress_hb\n" +
                "t_course_study_progress_gz\n" +
                "t_course_study_progress_gx\n" +
                "t_course_study_progress_gs\n" +
                "t_course_study_progress_gd\n" +
                "t_course_study_progress_fj\n" +
                "t_course_study_progress_eb\n" +
                "t_course_study_progress_cq\n" +
                "t_course_study_progress_cm\n" +
                "t_course_study_progress_bj\n" +
                "t_course_study_progress_ah\n";

//        String[] lines = sql.split("\\r?\\n");
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String s: lines){
//            stringBuilder.append("alter table ");
//            stringBuilder.append(s);
//            stringBuilder.append(" add f_subject_finish_time bigint null comment '专题必修课完成时间';");
//            stringBuilder.append("\n");
//        }
//
//        FileOutputStream fos = new FileOutputStream("D:/ddl.sql");
//        fos.write(stringBuilder.toString().getBytes());
//        fos.close();
        String[] lines = sql.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s: lines){
            stringBuilder.append(s).append("|");
        }
        System.out.println(stringBuilder.toString());
        throw new Exception("1");
    }





}
