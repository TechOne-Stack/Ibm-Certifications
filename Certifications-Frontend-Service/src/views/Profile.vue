<template>
  <div>
    <v-row>
      <v-col cols="12">
        <h1>Your Profile</h1>
      </v-col>
    </v-row>
    <v-divider />
    <!-- <v-row>
      <v-layout align-center justify-center>
        <v-flex md4 sm8 xs12 style="padding-top: 2em">
          <flashmessage></flashmessage>
        </v-flex>
      </v-layout>
    </v-row> -->
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
                <v-text-field
                  prepend-icon="mdi-account"
                  label="Enter your email"
                  type="text"
                  :counter="50"
                  v-model="userEmail"
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
import axios from "axios";
import flashmessage from "@/components/FlashMessage.vue";

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
      userSurName: "",
      userEmail: "",
      password: "",
      notifications: this.$store.getters.notifications
    };
  },
  // components: {
  //   flashmessage
  // },
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
        name: this.userFirstName,
        password: this.password,
        surname: this.userSurName
      });
    }
  },
  async created() {
    let userId = null;
    if (this.$store.getters.userId == null) {
      userId = localStorage.getItem("userID");
    } else {
      userId = this.$store.getters.userId;
    }
    const url = "http://localhost:8080/users/" + userId;
    const { data } = await axios.get(url);
    this.userFirstName = data.name;
    this.userSurName = data.surname;
    this.userEmail = data.email;
    this.password = data.password;
  }
};
</script>

<style></style>
