import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "@/views/Home.vue";
import Login from "@/views/Login.vue";
import CertificationDetail from "@/views/CertificationDetail.vue";
import Profile from "@/views/Profile.vue";
import Voucher from "@/views/Voucher.vue";
import VoucherDetail from "@/views/VoucherDetail.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/voucher",
    name: "Voucher",
    component: Voucher
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile
  },
  {
    path: "/certificationDetail/:id",
    name: "CertificationDetail",
    component: CertificationDetail
  },
  {
    path: "/voucherDetail/:id",
    name: "VoucherDetail",
    component: VoucherDetail
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
