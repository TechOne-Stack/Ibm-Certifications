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
    vouchers: [],
    voucherDetailData: [],
    voucherDetailUserData: [],
    listOfUsers: [],
    user: {},
    updateSuccess: false,
    certificateSuccess: false,
    admin: false,
    users: null
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
    vouchersMutation(state, value) {
      state.vouchers = value;
    },
    voucherDetailMutation(state, value) {
      state.voucherDetailData = value;
    },
    voucherDetailUserMutation(state, value) {
      state.voucherDetailUserData = value;
    },
    listOfUsersMutation(state, value) {
      state.listOfUsers = value;
    },
    userMutation(state, value){
      state.user = value;
    },
    updateSuccessMutation(state, value){
      state.updateSuccess = value;
    },
    certificateSuccessMutation(state, value){
      state.certificateSuccess = value;
    },
    usersMutation(state, value){
      state.users = value;
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
    vouchers(state: any) {
      return state.vouchers;
    },
    voucherDetailData(state: any) {
      return state.voucherDetailData;
    },
    voucherDetailUserData(state: any) {
      return state.voucherDetailUserData;
    },
    listOfUsers(state: any) {
      return state.listOfUsers;
    },
    user(state: any){
      return state.user;
    },
    updateSuccess(state: any){
      return state.updateSuccess;
    },
    certificateSuccess(state: any){
      return state.certificateSuccess;
    },
    admin(state: any){
      return localStorage.getItem("admin");
    },
    users(state: any){
      return state.users;
    }
  },
  actions: {
    //prihlasit sa do aplikacie
    async loginToApp({ commit, rootState }) {
      const API_URL = 'http://localhost:8080/api/auth/';
      try {
        const { data } = await axios.post(API_URL + 'signin', {email: this.state.email, password: this.state.password});
        localStorage.setItem("token", JSON.stringify(data.token));
        commit("loggedInMutation", true);
        localStorage.setItem("loggedIn", "true");
        localStorage.setItem("user", JSON.stringify(data));
        commit("userMutation", {firstname: data.firstname, surname: data.surname, email: data.email});
        if(data.roles.includes("ROLE_ADMIN")){
          localStorage.setItem("admin", "true");
        }
        router.push("/");
      } catch (err) {
        if(err.response.status == 401){
          alert("BAD CREDENTIALS");
        } else if(err.response){
          alert("RESPONSE ERROR - client received an error response");
        } else if (err.request){
          alert("REQUEST ERROR - client never received a response");
        } else {
          alert("something wrong");
        }
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
        commit("certificateSuccessMutation", true);
      } catch (err) {
        if(err.response){
          alert("RESPONSE ERROR - client received an error response");
        } else if (err.request){
          alert("REQUEST ERROR - client never received a response");
        } else {
          alert("something wrong");
        }
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
        if(err.response.status == 400){
          alert("EMAIL IS USED");
        } else if(err.response){
          alert("RESPONSE ERROR - client received an error response");
        } else if (err.request){
          alert("REQUEST ERROR - client never received a response");
        } else {
          alert("something wrong");
        }
      }
    },

    //update
    async sendUpdateRequest(
      { commit, rootState },
      updateRequest
    ) {
      const url = "http://localhost:8080/api/test/users/update/"
      const token = JSON.parse(localStorage.getItem("token") || '{}');
      try {
        const { data } = await axios.post(url, updateRequest, {
          headers: {
            "Content-Type": "application/json",
            Authorization: 'Bearer ' + token 
          }
        });
        commit("updateSuccessMutation", true);
      } catch (err) {
        if(err.response){
          alert("RESPONSE ERROR - client received an error response");
        } else if (err.request){
          alert("REQUEST ERROR - client never received a response");
        } else {
          alert("something wrong");
        }
      }
    },
    async createVoucherRequest({ commit, rootState }, voucherRequest) {
      const url = "http://localhost:8080/voucherRequest/";
      const token = JSON.parse(localStorage.getItem("token") || '{}');
      try {
        const { data } = await axios.post(url, voucherRequest, {
          headers: {
            "Content-Type": "application/json",
            Authorization: 'Bearer ' + token 
          }
        });
      } catch (err) {
        if(err.response){
          alert("RESPONSE ERROR - client received an error response");
        } else if (err.request){
          alert("REQUEST ERROR - client never received a response");
        } else {
          alert("something wrong");
        }
      }
    },

    async assignVoucherToUserRequest({ commit, rootState }, voucherUserRequest) {
      const url = "http://localhost:8080/voucher/" + voucherUserRequest.voucherId + "/user/" + voucherUserRequest.userId;
      const token = JSON.parse(localStorage.getItem("token") || '{}');
      try {
        const { data } = await axios.post(url, voucherUserRequest, {
          headers: {
            "Content-Type": "application/json",
            Authorization: 'Bearer ' + token 
          }
        });
      } catch (err) {
        console.log(err);
      }
    },

    async loadUsers({commit}){
      const user = JSON.parse(localStorage.getItem("user") || '{}');
      const token = JSON.parse(localStorage.getItem("token") || '{}');
      console.log(user);
      const { data } = await axios.get("http://localhost:8080/api/test/users/except/" + user.id, {
        headers: {
          Authorization: 'Bearer ' + token 
        }
      });
      commit("usersMutation", data);
      return data;
    } 
  },
  modules: {},
});
