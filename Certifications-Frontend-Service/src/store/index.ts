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
    certifications: []
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
    }
  },
  getters: {
    email(state: any) {
      return state.email;
    },
    password(state: any) {
      return state.password;
    },
    loggedIn(state: any){
      return state.loggedIn;
    },
    certifications(state: any){
      return state.certifications;
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
      try {
        const { data } = await axios.post(url, certificationRequest, {
          headers
        });
      } catch (err) {
        console.log(err);
      }
    }
  },
  modules: {}
});
