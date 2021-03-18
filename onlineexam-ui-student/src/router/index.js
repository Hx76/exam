import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "@/views/Login";
import Register from "@/views/Register";
import UserInfo from "@/views/UserInfo";
import ExamList from "@/views/ExamList";
import Exam from "@/views/Exam";
import ExamManage from "@/views/ExamManage";
import QuestionManage from "@/views/QuestionManage";
import Question from "@/views/Question";
import MyExam from "@/views/MyExam";
import MyCreatedExam from "@/views/MyCreatedExam";
import Rank from "@/views/Rank";
import ExamPaper from "@/views/ExamPaper";
Vue.use(VueRouter)



const routes = [
  {
    path: '/',
    name: 'login',
    component: Login,
    meta: '登录'
  },
  {
    path: '/myExam',
    name: 'myExam',
    component: MyExam
  },
  {
    path: '/myCreatedExam',
    name: 'myCreatedExam',
    component: MyCreatedExam
  },
  {
    path: '/rank',
    name: 'rank',
    component: Rank
  },
  {
    path: '/home',
    name: 'home',
    component: Home,
    meta: '首页'
  },
  {
    path: '/userInfo',
    name: 'userInfo',
    component: UserInfo
  },
  {
    path: '/examList',
    name: 'examList',
    component: ExamList
  },
  {
    path: '/examManage',
    name: 'examManage',
    component: ExamManage
  },
  {
    path: '/questionManage',
    name: 'questionManage',
    component: QuestionManage
  },
  {
    path: '/exam',
    name: 'exam',
    component: Exam
  },
  {
    path: '/question',
    name: 'question',
    component: Question
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/examPaper',
    name: 'examPaper',
    component: ExamPaper
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

