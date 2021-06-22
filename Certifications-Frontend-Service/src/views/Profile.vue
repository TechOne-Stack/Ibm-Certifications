<template>
  <div>
    <v-row>
      <v-col cols="12">
        <h1>Your Profile</h1>
      </v-col>
    </v-row>
    <v-divider />
    <v-row>
      <v-layout align-center justify-center>
        <v-flex md4 sm8 xs12 style="padding-top: 2em">
          <flash-message class="myCustomClass"></flash-message>
        </v-flex>
      </v-layout>
    </v-row>
    <v-row>
      <v-layout align-center justify-center>
        <v-flex md4 sm8 xs12 style="padding-top: 2em">
          <v-card class="elevation-12">
            <v-card-text>
              <p>Your credentials</p>
              <v-form ref="updateForm" v-model="validForm">
                <v-text-field
                  prepend-icon="mdi-account"
                  label="Enter your first name"
                  type="text"
                  v-model="userFirstName"
                ></v-text-field>
                <v-text-field
                  prepend-icon="mdi-account"
                  label="Enter your surname"
                  type="text"
                  v-model="userSurName"
                ></v-text-field>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-btn
                :disabled="!validForm"
                @click="sendUpdateRequest"
                class="success"
              >
                Update your profile</v-btn
              >
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-row>
    <v-row>
      <v-col cols="12" style="padding-top: 1em">
        <v-btn @click.stop="newCertificationDialog = true" color="primary"
          >New Certification Request
        </v-btn>
        <v-dialog v-model="newCertificationDialog" persistent max-width="600px">
          <v-card>
            <v-card-title>
              New Certification Request
            </v-card-title>
            <v-card-text>
              <v-form ref="newCertificationForm" v-model="valid">
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      label="Name"
                      :rules="nameRules"
                      v-model="certificationName"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <v-text-field
                      label="Price"
                      v-model="certificationPrice"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6">
                    <v-text-field
                      label="Currency"
                      :rules="currencyRules"
                      v-model="certificationCurrency"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      label="Url"
                      :rules="urlRules"
                      v-model="certificationUrl"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-spacer />
              <v-btn @click="closeNewCertificationDialog" color="danger"
                >Close</v-btn
              >
              <v-btn
                :disabled="!valid"
                @click="sendNewCertificationDialog"
                color="success"
                >Send</v-btn
              >
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Vue from "vue";
import VueFlashMessage from "vue-flash-message";
import { mapGetters } from "vuex";
Vue.use(VueFlashMessage);

export default {
  data() {
    return {
      valid: true,
      validForm: true,
      newCertificationDialog: false,
      certificationName: "",
      certificationPrice: 0,
      certificationCurrency: "",
      certificationUrl: "",
      nameRules: [v => !!v || "Name is required!"],
      currencyRules: [v => !!v || "Currency is required!"],
      urlRules: [v => !!v || "Url is required!"],
      userFirstName: "",
      userSurName: ""
    };
  },
  methods: {
    closeNewCertificationDialog() {
      this.newCertificationDialog = false;
      this.certificationName = "";
      this.certificationPrice = 0;
      this.certificationCurrency = "";
      this.certificationUrl = "";
    },
    sendNewCertificationDialog() {
      this.$store.dispatch("createCertificationRequest", {
        name: this.certificationName,
        currency: this.certificationCurrency,
        price: this.certificationPrice,
        url: this.certificationUrl
      });
      this.closeNewCertificationDialog();
    },
    sendUpdateRequest() {
      this.$store.dispatch("sendUpdateRequest", {
        email: this.userEmail,
        firstname: this.userFirstName,
        surname: this.userSurName
      });
    }
  },
  created() {
    let user;
    if (Object.keys(this.$store.getters.user).length === 0) {
      user = JSON.parse(localStorage.getItem("user"));
    } else {
      user = this.$store.getters.user;
    }
    this.userFirstName = user.firstname;
    this.userSurName = user.surname;
    this.userEmail = user.email;
  },
  computed: {
    ...mapGetters(["updateSuccess", "certificateSuccess"])
  },
  watch: {
    updateSuccess() {
      if (this.updateSuccess == true) {
        this.flash("Profile successfully updated", "success", {
          timeout: 2000
        });
        this.$store.commit("updateSuccessMutation", false);
        this.$store.commit("userMutation", {
          firstname: this.userFirstName,
          surname: this.userSurName,
          email: this.userEmail
        });
        const data = {
          firstname: this.userFirstName,
          surname: this.userSurName,
          email: this.userEmail
        };
        localStorage.setItem("user", JSON.stringify(data));
      }
    },
    certificateSuccess() {
      if (this.certificateSuccess == true) {
        this.flash("Certificate successfully created!", "success", {
          timeout: 2000
        });
        this.$store.commit("certificateSuccessMutation", false);
      }
    }
  }
};
</script>

<style></style>
