<template>
  <div class="home">
    <v-row>
      <v-col cols="12">
        <v-row>
          <v-col cols="12" style="padding-top: 1em; padding-bottom: 1em">
            <v-btn @click.stop="newVoucherDialog = true" color="primary"
              >New Voucher Request
            </v-btn>
            <v-dialog v-model="newVoucherDialog" persistent max-width="600px">
              <v-card>
                <v-card-title>
                  New Voucher Request
                </v-card-title>
                <v-card-text>
                  <v-form ref="newVoucherForm" v-model="valid">
                    <v-row>
                      <v-col class="d-flex" cols="12" sm="6">
                        <v-select
                          v-model="selectedState"
                          :items="voucherState"
                          label="State"
                        ></v-select>
                      </v-col>
                      <v-col cols="6">
                        <v-text-field
                          label="Voucher Code"
                          type="text"
                          :rules="voucherRules"
                          v-model="voucherCode"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="selectedCertification"
                          :items="certifications"
                          item-text="name"
                          item-value="id"
                          label="Certification"
                          required
                          :rules="certificationRules"
                        >
                        </v-select>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="selectedUser"
                          :items="listOfUsers.users"
                          item-text="surname"
                          item-value="id"
                          label="User"
                        >
                        </v-select>
                      </v-col>
                    </v-row>
                    <v-row justify="center">
                      <v-date-picker v-model="validUntil"></v-date-picker>
                    </v-row>
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-spacer />
                  <v-btn @click="closeNewVoucherDialog" color="danger"
                    >Close</v-btn
                  >
                  <v-btn
                    :disabled="!valid"
                    @click="sendNewVoucherDialog"
                    color="success"
                    >Send</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-col>
        </v-row>
        <v-divider />
        <h1>Available Vouchers</h1>
        <div v-if="vouchers !== undefined && vouchers.length > 0">
          <v-row>
            <router-link
              v-for="voucher in vouchers"
              :key="voucher.id"
              :to="'/voucherDetail/' + voucher.id"
              tag="div"
            >
              <v-col>
                <VoucherItem style="cursor: pointer" :voucher="voucher" />
              </v-col>
            </router-link>
          </v-row>
        </div>
        <div v-else>
          <h3>No Vouchers found</h3>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Vue from "vue";
import VoucherItem from "@/components/VoucherItem.vue";
import { mapGetters, mapMutations } from "vuex";
import axios from "axios";

export default {
  name: "Voucher",
  components: {
    VoucherItem,
  },
  data() {
    return {
      valid: true,
      newVoucherDialog: false,
      voucherState: ["NEW", "ACTIVE", "PROPOSED"],
      voucherCode: "",
      validUntil: new Date().toISOString().substr(0, 10),
      voucherRules: [
        (value) => value.length >= 5 || "Minimum 5 characters required",
      ],
      certificationRules: [ (value) => value != null || "Item is not selected!"],
      selectedUser: null,
      selectedCertification: null,
      selectedState: "NEW",
    };
  },
  computed: {
    ...mapGetters(["vouchers", "listOfUsers", "certifications"]),
  },
  async mounted() {
    if (this.loggedIn == false) {
      this.$router.push("/login");
    }
    this.mountedMock();
  },
  methods: {
    ...mapMutations([
      "vouchersMutation",
      "listOfUsersMutation",
      "certificationsMutation",
    ]),
    async mountedMock() {
      const token = JSON.parse(localStorage.getItem("token"));
      const { data } = await axios.get("http://localhost:8080/vouchers", {
      headers: {
        "Content-Type": "application/json",
        Authorization: 'Bearer ' + token 
      }
    });
      const users = await axios.get("http://localhost:8080/users/", {
      headers: {
        "Content-Type": "application/json",
        Authorization: 'Bearer ' + token 
      }
    });
      const certificationData = await axios.get(
        "http://localhost:8080/certifications/", {
      headers: {
        "Content-Type": "application/json",
        Authorization: 'Bearer ' + token 
      }
    });
      this.certificationsMutation(
        certificationData.data._embedded.certifications
      );
      this.listOfUsersMutation(users.data._embedded);
      this.vouchersMutation(data._embedded.vouchers);
    },
    closeNewVoucherDialog() {
      this.newVoucherDialog = false;
      this.voucherCode = "";
      this.validUntil = "";
      this.voucherState = null;
      this.selectedUser = null;
      this.selectedCertification = null;
    },
    sendNewVoucherDialog() {
      this.$store.dispatch("createVoucherRequest", {
        state: this.selectedState,
        validUntil: this.validUntil,
        voucherCode: this.voucherCode,
        certificationId: this.selectedCertification,
        userId: this.selectedUser,
      });
      this.mountedMock();
      this.closeNewVoucherDialog();
    },
  },
};
</script>

<style></style>
