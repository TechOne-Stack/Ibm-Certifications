<template>
  <v-container fluid fill-height>
    <v-layout align-center justify-center>
      <v-flex md8 sm10 xs12>
        <v-row>
          <v-col cols="12">
            <v-card>
              <v-card-title>
                Edit Certification
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
                <router-link to="/" tag="span" class="pointerClass">
                  <v-btn color="danger">Close</v-btn>
                </router-link>
                <v-btn
                  :disabled="!valid"
                  @click="sendUpdatedCertification"
                  color="success"
                  >Send</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "EditCertification",
  data() {
    return {
      valid: true,
      currentCertification: {},
      certificationName: "",
      certificationPrice: 0,
      certificationCurrency: "",
      certificationUrl: "",
      nameRules: [v => !!v || "Name is required!"],
      currencyRules: [v => !!v || "Currency is required!"],
      urlRules: [v => !!v || "Url is required!"]
    };
  },
  created() {
    this.currentCertification = this.certifications.find(
      ({ id }) => id == this.$route.params.id
    );
    this.certificationName = this.currentCertification.name;
    this.certificationPrice = this.currentCertification.price;
    this.certificationCurrency = this.currentCertification.currency;
    this.certificationUrl = this.currentCertification.url;
  },
  methods: {
    sendUpdatedCertification() {
      this.$store.dispatch("updateCertificationRequest", {
        id: this.currentCertification.id,
        name: this.certificationName,
        currency: this.certificationCurrency,
        price: this.certificationPrice,
        url: this.certificationUrl
      });
    }
  },
  computed: {
    ...mapGetters(["certifications"])
  }
};
</script>
