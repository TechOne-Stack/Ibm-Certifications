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
                  <v-row>
                    <v-col cols="12">
                      <ul id="editCertSkillList">
                        <li v-for="skillItem in skills" :key="skillItem.id">
                          <v-checkbox
                            v-model="certificationSkillList"
                            :label="skillItem.name"
                            :value="skillItem.id"
                          ></v-checkbox>
                        </li>
                      </ul>
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
import { mapGetters, mapMutations } from "vuex";
import axios from "axios";

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
      certificationSkillList: [],
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
  async mounted() {
    const { data } = await axios.get("http://localhost:8080/skills");
    let temp = [];
    temp = await axios.get(
      "http://localhost:8080/certifications/" +
        this.currentCertification.id +
        "/skills"
    );
    temp.data._embedded.skills.forEach( skill => {
      console.log(temp);
      this.certificationSkillList.push(skill.id);
    });
    this.skillsMutation(data._embedded.skills);
  },
  methods: {
    sendUpdatedCertification() {
      this.$store.dispatch("updateCertificationRequest", {
        certification: {
          id: this.currentCertification.id,
          name: this.certificationName,
          currency: this.certificationCurrency,
          price: this.certificationPrice,
          url: this.certificationUrl
        },
        skills: this.certificationSkillList
      });
    },
    ...mapMutations(["skillsMutation"])
  },
  computed: {
    ...mapGetters(["certifications"]),
    ...mapGetters(["skills"])
  }
};
</script>
