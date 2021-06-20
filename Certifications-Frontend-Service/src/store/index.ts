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
    userId: null,
    notificationsMessages: [
      { key: "updateSuccess", notification: "You have successfully updated your profile." },
      { key: "updateError", notification: "Please try again." },
    ],
    chosenMessage: '',
    notifications: false
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
    userIdMutation(state, value){
      state.userId = value;
    },
    updateSuccess(state, key){
      const msgResult = state.notificationsMessages.find(msg => {
         return msg.key == key;
      });
      if (msgResult !== undefined && msgResult.notification !== undefined) {
        state.chosenMessage = msgResult.notification;
      }
    },
    updateError(state, key){
      const msgResult = state.notificationsMessages.find(msg => {
         return msg.key == key;
      });
      if (msgResult !== undefined && msgResult.notification !== undefined) {
        state.chosenMessage = msgResult.notification;
      }
    },
    notificationsMutation(state, value) {
      state.notifications = value;
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
    userId(state: any){
      return state.userId;
    },
    messageToDisplay(state){
      return state.chosenMessage;
    },
    notifications(state){
      return state.notifications;
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
    async sendNewRegisterRequest(
      { commit, rootState },
      registerRequest
    ) {
      const url = "http://localhost:8080/users/";
      const headers = {
        "Content-Type": "application/json",
        Autorization: localStorage.getItem("token")
      };
      try {
        const { data } = await axios.post(url, registerRequest, {
          headers
        });
        commit("userIdMutation", data.id);
        localStorage.setItem("userID", data.id);
      } catch (err) {
        console.log(err);
      }
      commit("loggedInMutation", true);
      localStorage.setItem("loggedIn", "true");
      router.push("/");
    },
    async sendUpdateRequest(
      { commit, rootState },
      updateRequest
    ) {
      let userId = null;
      if (rootState.userId == null){
        userId = localStorage.getItem("userID");
      } else {
        userId = rootState.userId;
      }
      const url = "http://localhost:8080/users/" + userId;
      const headers = {
        "Content-Type": "application/json",
        Autorization: localStorage.getItem("token")
      };
      try {
        const { data } = await axios.put(url, updateRequest, {
          headers
        });
        const key = 'updateSuccess';
        commit('updateSuccess', key);
      } catch (err) {
        console.log(err);
        const key = 'updateError';
        commit('updateError', key);
      }
      commit('notificationsMutation', true);
    }
  },
  modules: {}
});
