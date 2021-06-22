import Vue from "vue";
import Vuex from "vuex";
import router from "../router/index";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    email: "",
    password: "",
    loggedIn: false,
    certifications: [],
    skills: [],
    vouchers: [],
    voucherDetailData: [],
    voucherDetailUserData: [],
  },
  mutations: {
    emailMutation(state, value) {
      state.email = value;
    },
    passwordMutation(state, value) {
      state.password = value;
    },
    loggedInMutation(state, value) {
      console.log("loggedInMutation: " + value);
      if (value === null) {
        state.loggedIn = false;
      } else {
        state.loggedIn = value;
      }
    },
    certificationsMutation(state, value) {
      state.certifications = value;
    },
    skillsMutation(state, value) {
      state.skills = value;
    },
    vouchersMutation(state, value) {
      state.vouchers = value;
    },
    voucherDetailMutation(state, value) {
      state.voucherDetailData = value;
    },
    voucherDetailUserMutation(state, value) {
      state.voucherDetailUserData = value;
    }
  },
  getters: {
    email(state: any) {
      return state.email;
    },
    password(state: any) {
      return state.password;
    },
    loggedIn(state: any) {
      return state.loggedIn;
    },
    certifications(state: any) {
      return state.certifications;
    },
    skills(state: any){
      return state.skills;
    },
    vouchers(state: any) {
      return state.vouchers;
    },
    voucherDetailData(state: any) {
      return state.voucherDetailData;
    },
    voucherDetailUserData(state: any) {
      return state.voucherDetailUserData;
    }
  },
  actions: {
    loginToApp({ commit, rootState }) {
      //TODO login via backend API
      commit("loggedInMutation", true);
      localStorage.setItem("loggedIn", "true");
      router.push("/");
    },
    async createCertificationRequest(
      { commit, rootState },
      certificationRequest
    ) {
      const url = "http://localhost:8080/certifications/";
      const headers = {
        "Content-Type": "application/json",
        Autorization: localStorage.getItem("token")
      };
      try {
        const { data } = await axios.post(url, certificationRequest, {
          headers
        });
      } catch (err) {
        console.log(err);
      }
    },
    async updateCertificationRequest(
      { commit, rootState },
      certificationRequest
    ) {
      const url = "http://localhost:8080/update-certificate";
      const headers = {
        "Content-Type": "application/json",
        Autorization: localStorage.getItem("token")
      };
      try{
      const { data } = await axios.post(url, certificationRequest, {
        headers});
      } catch (err) {
        console.log(err);
      }
    },
    async createVoucherRequest({ commit, rootState }, voucherRequest) {
      const url = "http://localhost:8080/vouchers/";
      const headers = {
        "Content-Type": "application/json",
        Autorization: localStorage.getItem("token")
      };
      try {
        const { data } = await axios.post(url, voucherRequest, {
          headers
        });
      } catch (err) {
        console.log(err);
      }
    }
  },
  modules: {}
});
