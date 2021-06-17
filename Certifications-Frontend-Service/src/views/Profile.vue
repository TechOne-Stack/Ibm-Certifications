<template>
  <div>
    <v-row>
      <v-col cols="12">
        <h1>Your Profile</h1>
      </v-col>
    </v-row>
    <v-divider />
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
export default {
  data() {
    return {
      valid: true,
      newCertificationDialog: false,
      certificationName: "",
      certificationPrice: 0,
      certificationCurrency: "",
      certificationUrl: "",
      nameRules: [v => !!v || "Name is required!"],
      currencyRules: [v => !!v || "Currency is required!"],
      urlRules: [v => !!v || "Url is required!"]
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
    }
  }
};
</script>

<style></style>
