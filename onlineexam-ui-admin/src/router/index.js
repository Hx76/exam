import Vue from 'vue';
import Router from 'vue-router';
import ExamPaper from "@/components/page/ExamPaper";

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/index',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: { title: '登录' }
        },
        {
            path: '/examPaper',
            name: 'examPaper',
            component: ExamPaper,
            meta: { title: '试卷' }
        },
        {
            path: '/',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/dashboard',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/examManage',
                    component: () => import(/* webpackChunkName: "icon" */ '../components/page/ExamManage.vue'),
                    meta: { title: '考试管理' }
                },
                {
                    path: '/userManage',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/UserManage.vue'),
                    meta: { title: '用户管理' }
                },
                {
                    path: '/questionManage',
                    component: () => import(/* webpackChunkName: "chart" */ '../components/page/QuestionManage.vue'),
                    meta: { title: '题库管理' }
                },

                {
                    path: '/scoreManage',
                    component: () => import(/* webpackChunkName: "i18n" */ '../components/page/ScoreManage.vue'),
                    meta: { title: '成绩管理' }
                },
                {
                    // 权限页面
                    path: '/nacos',
                    component: () => import(/* webpackChunkName: "nacos" */ '../components/page/Nacos.vue'),
                    meta: { title: '服务配置', permission: true }
                },
                {
                    path: '/sentinel',
                    component: () => import(/* webpackChunkName: "sentinel" */ '../components/page/Sentinel.vue'),
                    meta: { title: '服务治理' }
                },
                {
                    path: '/indexManage',
                    component: () => import(/* webpackChunkName: "index" */ '../components/page/Index'),
                    meta: { title: '索引管理' }
                },
                {
                    path: '/kibana',
                    component: () => import(/* webpackChunkName: "kibana" */ '../components/page/Kibana.vue'),
                    meta: { title: 'kibana' }
                },
                {
                    path: '/swagger',
                    component: () => import(/* webpackChunkName: "swagger" */ '../components/page/Swagger.vue'),
                    meta: { title: '接口文档' }
                },
                {
                    path: '/druid',
                    component: () => import(/* webpackChunkName: "druid" */ '../components/page/Druid.vue'),
                    meta: { title: '数据源管理' }
                },
                {
                    path: '/activemq',
                    component: () => import(/* webpackChunkName: "activemq" */ '../components/page/ActiveMq.vue'),
                    meta: { title: '消息队列管理' }
                },

            ]
        },

        {
            path: '*',
            redirect: '/404'
        }
    ]
});
