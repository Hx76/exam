import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/index',
            redirect: '/dashboard'
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
                    path: '/permission',
                    component: () => import(/* webpackChunkName: "permission" */ '../components/page/Permission.vue'),
                    meta: { title: '权限测试', permission: true }
                },
                {
                    path: '/404',
                    component: () => import(/* webpackChunkName: "404" */ '../components/page/404.vue'),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: () => import(/* webpackChunkName: "403" */ '../components/page/403.vue'),
                    meta: { title: '403' }
                },

            ]
        },
        {
            path: '/',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: { title: '登录' }
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
