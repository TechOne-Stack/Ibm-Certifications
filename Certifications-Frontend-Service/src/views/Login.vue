<template>
  <v-container fluid fill-height>
    <v-layout align-center justify-center>
      <v-flex md4 sm8 xs12>
        <v-card class="elevation-12">
          <v-toolbar dark color="dark">
            <v-toolbar-title>Welcome to Certification app</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <p>Please Enter your credentials</p>
            <v-form ref="loginForm" v-model="validForm">
              <v-text-field
                prepend-icon="mdi-account"
                label="Enter your email"
                type="text"
                :counter="20"
                :rules="emailRules"
                v-model="email"
              ></v-text-field>
              <v-text-field
                prepend-icon="mdi-fingerprint"
                label="Enter your password"
                type="password"
                :rules="passwordRules"
                v-model="password"
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-btn :disabled="!validForm" @click="loginToApp" class="success">
              Login</v-btn
            >
          </v-card-actions>
          <div>
            <v-row>
              <v-col cols="12" style="padding-top: 1em">
                <h2>Don't have an account?</h2>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" style="padding-top: 0.5em">
                <v-btn @click="registerToApp">
                  Sign up
                </v-btn>
              </v-col>
            </v-row>
          </div>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "Login",
  data() {
    return {
      validForm: true,
      emailRules: [
        value => !!value || "Email is required!",
        value => /.+@.+/.test(value) || "Email must be valid"
      ],
      passwordRules: [value => value.length >= 8 || "Minimum 8 characters"]
    };
  },
  computed: {
    email: {
      get() {
        return this.$store.getters.email;
      },
      set(value) {
        this.$store.commit("emailMutation", value);
      }
    },
    password: {
      get() {
        return this.$store.getters.password;
      },
      set(value) {
        this.$store.commit("passwordMutation", value);
      }
    }
  },
  methods: {
    ...mapActions(["loginToApp"]),
    registerToApp() {
      this.$router.push("/register");
    }
  }
};
</script>
