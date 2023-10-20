// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: scalardb.proto

package com.scalar.db.rpc;

public interface TwoPhaseCommitTransactionResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:rpc.TwoPhaseCommitTransactionResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.StartResponse start_response = 1;</code>
   * @return Whether the startResponse field is set.
   */
  boolean hasStartResponse();
  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.StartResponse start_response = 1;</code>
   * @return The startResponse.
   */
  com.scalar.db.rpc.TwoPhaseCommitTransactionResponse.StartResponse getStartResponse();
  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.StartResponse start_response = 1;</code>
   */
  com.scalar.db.rpc.TwoPhaseCommitTransactionResponse.StartResponseOrBuilder getStartResponseOrBuilder();

  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.GetResponse get_response = 2;</code>
   * @return Whether the getResponse field is set.
   */
  boolean hasGetResponse();
  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.GetResponse get_response = 2;</code>
   * @return The getResponse.
   */
  com.scalar.db.rpc.TwoPhaseCommitTransactionResponse.GetResponse getGetResponse();
  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.GetResponse get_response = 2;</code>
   */
  com.scalar.db.rpc.TwoPhaseCommitTransactionResponse.GetResponseOrBuilder getGetResponseOrBuilder();

  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.ScanResponse scan_response = 3;</code>
   * @return Whether the scanResponse field is set.
   */
  boolean hasScanResponse();
  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.ScanResponse scan_response = 3;</code>
   * @return The scanResponse.
   */
  com.scalar.db.rpc.TwoPhaseCommitTransactionResponse.ScanResponse getScanResponse();
  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.ScanResponse scan_response = 3;</code>
   */
  com.scalar.db.rpc.TwoPhaseCommitTransactionResponse.ScanResponseOrBuilder getScanResponseOrBuilder();

  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.Error error = 4;</code>
   * @return Whether the error field is set.
   */
  boolean hasError();
  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.Error error = 4;</code>
   * @return The error.
   */
  com.scalar.db.rpc.TwoPhaseCommitTransactionResponse.Error getError();
  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.Error error = 4;</code>
   */
  com.scalar.db.rpc.TwoPhaseCommitTransactionResponse.ErrorOrBuilder getErrorOrBuilder();

  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.BeginResponse begin_response = 5;</code>
   * @return Whether the beginResponse field is set.
   */
  boolean hasBeginResponse();
  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.BeginResponse begin_response = 5;</code>
   * @return The beginResponse.
   */
  com.scalar.db.rpc.TwoPhaseCommitTransactionResponse.BeginResponse getBeginResponse();
  /**
   * <code>.rpc.TwoPhaseCommitTransactionResponse.BeginResponse begin_response = 5;</code>
   */
  com.scalar.db.rpc.TwoPhaseCommitTransactionResponse.BeginResponseOrBuilder getBeginResponseOrBuilder();

  public com.scalar.db.rpc.TwoPhaseCommitTransactionResponse.ResponseCase getResponseCase();
}