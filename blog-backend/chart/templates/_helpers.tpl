{{/*
Expand the name of the chart.
*/}}
{{- define "chart.name" -}}
{{ .Chart.Name }}
{{- end -}}

{{/*
Create a default fully qualified app name.
*/}}
{{- define "chart.fullname" -}}
{{- printf "%s-%s" (include "chart.name" .) .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Common labels for the chart.
*/}}
{{- define "chart.labels" -}}
app.kubernetes.io/name: {{ include "chart.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/version: {{ .Chart.AppVersion }}
app.kubernetes.io/managed-by: Helm
{{- end -}}

{{/*
Create chart app version.
*/}}
{{- define "chart.chart" -}}
{{ .Chart.Name }}-{{ .Chart.Version }}
{{- end -}}
