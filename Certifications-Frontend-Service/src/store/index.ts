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
    user: {}
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
    userMutation(state, value){
      state.user = value;
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
    },
    user(state: any){
      return state.user;
    },
  },
  actions: {
    //prihlasit sa do aplikacie
    async loginToApp({ commit, rootState }) {
      const API_URL = 'http://localhost:8080/api/auth/';
      try {
        const { data } = await axios.post(API_URL + 'signin', {email: this.state.email, password: this.state.password});
        localStorage.setItem("token", JSON.stringify(data.token));
        console.log(data);
        commit("loggedInMutation", true);
        localStorage.setItem("loggedIn", "true");
        localStorage.setItem("user", JSON.stringify(data));
        commit("userMutation", {firstname: data.firstname, surname: data.surname, email: data.email});
        router.push("/");
      } catch (err) {
        console.log(err);
      }
    },
    //vytvorit certifikat
    async createCertificationRequest(
      { commit, rootState },
      certificationRequest
    ) {
      const url = "http://localhost:8080/certifications/";
      const token = JSON.parse(localStorage.getItem("token") || '{}');
      try {
        const { data } = await axios.post(url, certificationRequest, {
          headers: {
            "Content-Type": "application/json",
            Authorization: 'Bearer ' + token 
          }
        });
      } catch (err) {
        console.log(err);
      }
    },

    //register
    async sendNewRegisterRequest(
      { commit, rootState },
      registerRequest
    ) {
      const url = "http://localhost:8080/api/auth/signup";
      try {
        const { data } = await axios.post(url, registerRequest);
        router.push("/login");
      } catch (err) {
        console.log(err);
      }
    },

    //update
    async sendUpdateRequest(
      { commit, rootState },
      updateRequest
    ) {
      const url = "http://localhost:8080/users/"
      const token = JSON.parse(localStorage.getItem("token") || '{}');
      try {
        const { data } = await axios.put(url, updateRequest, {
          headers: {
            "Content-Type": "application/json",
            Authorization: 'Bearer ' + token 
          }
        });
        const key = 'updateSuccess';
      } catch (err) {
        console.log(err);
        const key = 'updateError';
      }
    }
  },
  modules: {}
});
